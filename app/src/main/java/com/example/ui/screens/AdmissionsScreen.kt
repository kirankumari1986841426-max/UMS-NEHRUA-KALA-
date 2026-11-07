package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AdmissionDocument
import com.example.ui.theme.SchoolBlue
import com.example.ui.theme.SchoolGold
import com.example.ui.theme.SchoolGoldDark
import com.example.ui.theme.SchoolInk
import com.example.ui.theme.SchoolMuted
import com.example.ui.theme.SchoolNavy
import com.example.ui.theme.SchoolNavyDark
import com.example.ui.theme.SchoolPale
import com.example.ui.viewmodel.SchoolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdmissionsScreen(
    viewModel: SchoolViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = viewModel.repository
    val checkedDocs by viewModel.checkedDocs.collectAsState()
    val inquiryForm by viewModel.inquiryForm.collectAsState()
    val submittedInquiries by repository.submittedInquiries.collectAsState()
    val readinessPercentage = viewModel.getReadinessPercentage()

    val classOptions = listOf(
        "Class 1 (Age 6+)", "Class 2 (Age 7+)", "Class 3 (Age 8+)",
        "Class 4 (Age 9+)", "Class 5 (Age 10+)", "Class 6 (Age 11+)",
        "Class 7 (Age 12+)", "Class 8 (Age 13+)"
    )
    var isClassDropdownExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolPale),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Formalities Header
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SchoolNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "JOIN OUR SCHOOL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Admission Formalities",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Please keep the following documents and details ready for the admission process at UMS Nehrua Kala, Panchdeori, Gopalganj.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFDCE7F5),
                            lineHeight = 20.sp
                        )
                    )
                }
            }
        }

        // 2. Interactive Document Checklist with Progress
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "CHECKLIST TRACKER",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = SchoolBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Required Documents",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontFamily = FontFamily.Serif,
                                    color = SchoolNavy
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (readinessPercentage == 100) Color(0xFFE8F5E9) else Color(0xFFFFF8E1))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$readinessPercentage% Ready",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (readinessPercentage == 100) Color(0xFF2E7D32) else Color(0xFFF57F17)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { readinessPercentage / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = SchoolGold,
                        trackColor = Color(0xFFE2EAF4)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Tap any document below to tick it off your readiness checklist:",
                        style = MaterialTheme.typography.bodySmall.copy(color = SchoolMuted)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    repository.admissionDocuments.forEachIndexed { index, doc ->
                        val isChecked = checkedDocs.contains(doc.id)
                        DocumentItemRow(
                            index = index + 1,
                            doc = doc,
                            isChecked = isChecked,
                            onToggle = { viewModel.toggleDocumentChecked(doc.id) }
                        )
                        if (index < repository.admissionDocuments.lastIndex) {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF7FAFF), RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFFDCE7F5), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "💡 Note: For confirmation or further admission-related information, contact Vice Principal Mr. Janardan Ojha directly at 9471471275.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = SchoolNavy,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        )
                    }
                }
            }
        }

        // 3. Admission Inquiry / Pre-Registration Form
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "ONLINE PRE-REGISTRATION",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Submit Admission Inquiry",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Serif,
                            color = SchoolNavy
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Fill in student details to receive guidance and prepare school admission formalities.",
                        style = MaterialTheme.typography.bodySmall.copy(color = SchoolMuted)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Student Name
                    OutlinedTextField(
                        value = inquiryForm.studentName,
                        onValueChange = { viewModel.updateInquiryForm(studentName = it) },
                        label = { Text("Student Full Name *") },
                        placeholder = { Text("e.g. Rahul Kumar") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_student_name"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SchoolNavy,
                            focusedLabelColor = SchoolNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Parent Name
                    OutlinedTextField(
                        value = inquiryForm.parentName,
                        onValueChange = { viewModel.updateInquiryForm(parentName = it) },
                        label = { Text("Parent / Guardian Name *") },
                        placeholder = { Text("e.g. Ramesh Ojha") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_parent_name"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SchoolNavy,
                            focusedLabelColor = SchoolNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Contact Phone
                    OutlinedTextField(
                        value = inquiryForm.phone,
                        onValueChange = { viewModel.updateInquiryForm(phone = it) },
                        label = { Text("Mobile Contact Number *") },
                        placeholder = { Text("10-digit mobile number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_phone"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SchoolNavy,
                            focusedLabelColor = SchoolNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Target Class Dropdown
                    ExposedDropdownMenuBox(
                        expanded = isClassDropdownExpanded,
                        onExpandedChange = { isClassDropdownExpanded = !isClassDropdownExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = inquiryForm.targetClass,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Seeking Admission In Class *") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isClassDropdownExpanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = SchoolNavy,
                                focusedLabelColor = SchoolNavy
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = isClassDropdownExpanded,
                            onDismissRequest = { isClassDropdownExpanded = false }
                        ) {
                            classOptions.forEach { opt ->
                                DropdownMenuItem(
                                    text = { Text(opt) },
                                    onClick = {
                                        viewModel.updateInquiryForm(targetClass = opt)
                                        isClassDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Remarks
                    OutlinedTextField(
                        value = inquiryForm.remarks,
                        onValueChange = { viewModel.updateInquiryForm(remarks = it) },
                        label = { Text("Previous School or Any Questions (Optional)") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SchoolNavy,
                            focusedLabelColor = SchoolNavy
                        )
                    )

                    // Error Message display
                    inquiryForm.errorMessage?.let { errorMsg ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "⚠️ $errorMsg",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFC62828),
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    // Success Banner
                    AnimatedVisibility(visible = inquiryForm.isSuccess) {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFF81C784), RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = Color(0xFF2E7D32),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Inquiry Saved Successfully! You can now send it to the school via SMS or call directly.",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF1B5E20),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { viewModel.submitInquiry() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SchoolNavy,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("btn_save_inquiry")
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Save Inquiry")
                        }

                        Button(
                            onClick = {
                                val message = "Hello UMS Nehrua Kala, I am interested in admission for my child ${inquiryForm.studentName.ifBlank { "Student" }} in ${inquiryForm.targetClass}. Parent Contact: ${inquiryForm.phone}."
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse("sms:${repository.schoolPhone}?body=${Uri.encode(message)}")
                                }
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SchoolGold,
                                contentColor = SchoolNavyDark
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("btn_send_sms")
                        ) {
                            Icon(imageVector = Icons.Default.Message, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Send SMS")
                        }
                    }
                }
            }
        }

        // 4. Saved Inquiries History (if any)
        if (submittedInquiries.isNotEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "SAVED INQUIRIES (${submittedInquiries.size})",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SchoolBlue,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        submittedInquiries.forEach { inquiry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FAFF)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDCE7F5)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = inquiry.studentName,
                                            fontWeight = FontWeight.Bold,
                                            color = SchoolNavyDark
                                        )
                                        Text(
                                            text = inquiry.seekingClass,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = SchoolGoldDark,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                    Text(
                                        text = "Parent: ${inquiry.parentName} • Ph: ${inquiry.contactPhone}",
                                        style = MaterialTheme.typography.bodySmall.copy(color = SchoolMuted)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DocumentItemRow(
    index: Int,
    doc: AdmissionDocument,
    isChecked: Boolean,
    onToggle: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isChecked) Color(0xFFF0F7FF) else Color(0xFFFAFBFD)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isChecked) SchoolBlue else Color(0xFFE2EAF4)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .testTag("doc_row_${doc.id}")
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(if (isChecked) SchoolNavy else SchoolGold),
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text(
                        text = "$index",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = SchoolNavyDark
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = doc.title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = SchoolNavyDark
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    if (doc.isMandatory) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFFFEBEE))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Required",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFFC62828),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = doc.requirement,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF2C3E50),
                        lineHeight = 17.sp
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "💡 ${doc.tip}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = SchoolMuted,
                        fontSize = 11.5.sp
                    )
                )
            }
        }
    }
}
