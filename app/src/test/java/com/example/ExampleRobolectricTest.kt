package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.repository.SchoolRepository
import com.example.ui.viewmodel.InquiryFormState
import com.example.ui.viewmodel.SchoolNavTab
import com.example.ui.viewmodel.SchoolViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("UMS Nehrua Kala", appName)
    }

    @Test
    fun `repository provides correct school contact and principal info`() {
        val repository = SchoolRepository()
        assertEquals("9471471275", repository.schoolPhone)
        assertEquals("Mr. Janardan Ojha", repository.vicePrincipalName)
        assertTrue(repository.values.isNotEmpty())
        assertTrue(repository.admissionDocuments.isNotEmpty())
        assertTrue(repository.notices.isNotEmpty())
    }

    @Test
    fun `viewmodel handles tab navigation and document checklist calculation`() {
        val viewModel = SchoolViewModel()
        assertEquals(SchoolNavTab.HOME, viewModel.selectedTab.value)

        viewModel.selectTab(SchoolNavTab.ADMISSIONS)
        assertEquals(SchoolNavTab.ADMISSIONS, viewModel.selectedTab.value)

        val initialPct = viewModel.getReadinessPercentage()
        assertTrue(initialPct in 0..100)

        // Check toggling a doc
        viewModel.toggleDocumentChecked("photograph")
        assertTrue(viewModel.checkedDocs.value.contains("photograph"))
    }

    @Test
    fun `viewmodel validates and submits admission inquiry`() {
        val viewModel = SchoolViewModel()

        // Empty submit fails validation
        val failResult = viewModel.submitInquiry()
        assertFalse(failResult)
        assertFalse(viewModel.inquiryForm.value.isSuccess)

        // Valid submit succeeds
        viewModel.updateInquiryForm(
            studentName = "Rahul Kumar",
            parentName = "Ramesh Ojha",
            phone = "9876543210",
            targetClass = "Class 1"
        )
        val successResult = viewModel.submitInquiry()
        assertTrue(successResult)
        assertTrue(viewModel.inquiryForm.value.isSuccess)
        assertEquals(1, viewModel.repository.submittedInquiries.value.size)
    }
}
