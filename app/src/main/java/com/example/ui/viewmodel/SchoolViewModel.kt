package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.AdmissionInquiry
import com.example.data.model.NoticeCategory
import com.example.data.model.SchoolNotice
import com.example.data.repository.SchoolRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class SchoolNavTab(val label: String, val iconName: String) {
    HOME("Home", "home"),
    ABOUT("About", "info"),
    ACADEMICS("Academics", "school"),
    ADMISSIONS("Admissions", "assignment"),
    NOTICES("Notices", "notifications"),
    CONTACT("Contact", "call")
}

data class InquiryFormState(
    val studentName: String = "",
    val parentName: String = "",
    val phone: String = "",
    val studentAge: String = "6",
    val targetClass: String = "Class 1",
    val previousSchool: String = "",
    val remarks: String = "",
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

class SchoolViewModel(
    val repository: SchoolRepository = SchoolRepository()
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(SchoolNavTab.HOME)
    val selectedTab: StateFlow<SchoolNavTab> = _selectedTab.asStateFlow()

    private val _selectedNoticeCategory = MutableStateFlow(NoticeCategory.ALL)
    val selectedNoticeCategory: StateFlow<NoticeCategory> = _selectedNoticeCategory.asStateFlow()

    private val _noticeSearchQuery = MutableStateFlow("")
    val noticeSearchQuery: StateFlow<String> = _noticeSearchQuery.asStateFlow()

    // Document checklist state (set of document IDs checked by the user)
    private val _checkedDocs = MutableStateFlow<Set<String>>(
        setOf("aadhaar_child", "aadhaar_parents") // sensible starting default
    )
    val checkedDocs: StateFlow<Set<String>> = _checkedDocs.asStateFlow()

    // Inquiry form state
    private val _inquiryForm = MutableStateFlow(InquiryFormState())
    val inquiryForm: StateFlow<InquiryFormState> = _inquiryForm.asStateFlow()

    // Filtered notices
    val filteredNotices: StateFlow<List<SchoolNotice>> = combine(
        _selectedNoticeCategory,
        _noticeSearchQuery
    ) { category, query ->
        repository.notices.filter { notice ->
            val matchesCategory = (category == NoticeCategory.ALL) || (notice.category == category)
            val matchesQuery = query.isBlank() ||
                notice.title.contains(query, ignoreCase = true) ||
                notice.summary.contains(query, ignoreCase = true) ||
                notice.details.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), repository.notices)

    fun selectTab(tab: SchoolNavTab) {
        _selectedTab.value = tab
    }

    fun setNoticeCategory(category: NoticeCategory) {
        _selectedNoticeCategory.value = category
    }

    fun setNoticeSearchQuery(query: String) {
        _noticeSearchQuery.value = query
    }

    fun toggleDocumentChecked(docId: String) {
        val current = _checkedDocs.value.toMutableSet()
        if (current.contains(docId)) {
            current.remove(docId)
        } else {
            current.add(docId)
        }
        _checkedDocs.value = current
    }

    fun getReadinessPercentage(): Int {
        val totalMandatory = repository.admissionDocuments.count { it.isMandatory }
        if (totalMandatory == 0) return 100
        val checkedMandatory = repository.admissionDocuments.count { it.isMandatory && _checkedDocs.value.contains(it.id) }
        return ((checkedMandatory.toFloat() / totalMandatory) * 100).toInt().coerceIn(0, 100)
    }

    fun updateInquiryForm(
        studentName: String? = null,
        parentName: String? = null,
        phone: String? = null,
        studentAge: String? = null,
        targetClass: String? = null,
        previousSchool: String? = null,
        remarks: String? = null
    ) {
        _inquiryForm.value = _inquiryForm.value.copy(
            studentName = studentName ?: _inquiryForm.value.studentName,
            parentName = parentName ?: _inquiryForm.value.parentName,
            phone = phone ?: _inquiryForm.value.phone,
            studentAge = studentAge ?: _inquiryForm.value.studentAge,
            targetClass = targetClass ?: _inquiryForm.value.targetClass,
            previousSchool = previousSchool ?: _inquiryForm.value.previousSchool,
            remarks = remarks ?: _inquiryForm.value.remarks,
            errorMessage = null,
            isSuccess = false
        )
    }

    fun submitInquiry(): Boolean {
        val state = _inquiryForm.value
        if (state.studentName.trim().isBlank()) {
            _inquiryForm.value = state.copy(errorMessage = "Please enter student name")
            return false
        }
        if (state.parentName.trim().isBlank()) {
            _inquiryForm.value = state.copy(errorMessage = "Please enter parent/guardian name")
            return false
        }
        if (state.phone.trim().length < 10) {
            _inquiryForm.value = state.copy(errorMessage = "Please enter a valid 10-digit contact number")
            return false
        }

        val ageInt = state.studentAge.toIntOrNull() ?: 6
        val inquiry = AdmissionInquiry(
            studentName = state.studentName.trim(),
            parentName = state.parentName.trim(),
            contactPhone = state.phone.trim(),
            seekingClass = state.targetClass,
            studentAge = ageInt,
            remarks = state.remarks.trim()
        )
        repository.saveInquiry(inquiry)
        _inquiryForm.value = state.copy(
            isSuccess = true,
            errorMessage = null
        )
        return true
    }

    fun resetInquirySuccess() {
        _inquiryForm.value = InquiryFormState()
    }
}
