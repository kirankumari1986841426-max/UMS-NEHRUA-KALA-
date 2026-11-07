package com.example.data.model

data class SchoolNotice(
    val id: String,
    val title: String,
    val titleHi: String = "",
    val date: String,
    val category: NoticeCategory,
    val summary: String,
    val summaryHi: String = "",
    val details: String,
    val detailsHi: String = "",
    val isUrgent: Boolean = false
)

enum class NoticeCategory(val label: String, val labelHi: String) {
    ALL("All Notices", "सभी सूचनाएं"),
    ADMISSIONS("Admissions", "नामांकन"),
    EXAMS("Exams & Results", "परीक्षा व परिणाम"),
    HOLIDAYS("Holidays", "अवकाश"),
    MDM("Mid-Day Meal", "मध्याह्न भोजन"),
    EVENTS("Activities", "गतिविधियां")
}

data class SchoolValue(
    val title: String,
    val titleHi: String,
    val description: String,
    val descriptionHi: String,
    val iconEmoji: String
)

data class GradeInfo(
    val standard: String,
    val standardHi: String,
    val ageGroup: String,
    val subjects: List<String>,
    val description: String,
    val descriptionHi: String
)

data class MdmMenuItem(
    val day: String,
    val dayHi: String,
    val mealName: String,
    val mealNameHi: String,
    val details: String,
    val detailsHi: String,
    val nutritionNotes: String
)

data class AdmissionDocument(
    val id: String,
    val title: String,
    val titleHi: String,
    val requirement: String,
    val requirementHi: String,
    val tip: String,
    val tipHi: String,
    val isMandatory: Boolean = true
)

data class FormalAdmissionApplication(
    val applicationId: String,
    val studentName: String,
    val fatherName: String,
    val motherName: String,
    val dob: String,
    val gender: String,
    val category: String,
    val seekingClass: String,
    val phone: String,
    val aadhaarNo: String,
    val bankAccountNo: String,
    val bankIfsc: String,
    val villageAddress: String,
    val previousSchool: String,
    val submittedTimestamp: Long = System.currentTimeMillis()
)

data class SchoolHoliday(
    val name: String,
    val nameHi: String,
    val date: String,
    val days: String,
    val type: String
)

data class FaqItem(
    val question: String,
    val questionHi: String,
    val answer: String,
    val answerHi: String,
    val category: String
)
