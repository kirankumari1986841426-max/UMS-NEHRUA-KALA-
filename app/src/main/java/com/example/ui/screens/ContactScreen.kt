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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FaqItem
import com.example.ui.theme.SchoolBlue
import com.example.ui.theme.SchoolGold
import com.example.ui.theme.SchoolGoldDark
import com.example.ui.theme.SchoolInk
import com.example.ui.theme.SchoolMuted
import com.example.ui.theme.SchoolNavy
import com.example.ui.theme.SchoolNavyDark
import com.example.ui.theme.SchoolPale
import com.example.ui.viewmodel.SchoolViewModel

@Composable
fun ContactScreen(
    viewModel: SchoolViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = viewModel.repository

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolPale),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Contact Hero Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SchoolNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "GET IN TOUCH",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "We Welcome Your Questions",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Contact UMS Nehrua Kala for admissions, student assistance, and school information.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFDCE7F5),
                            lineHeight = 20.sp
                        )
                    )
                }
            }
        }

        // 2. Vice Principal & Direct Call Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "PRIMARY SCHOOL CONTACT",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Vice Principal & Administration",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Serif,
                            color = SchoolNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    ContactDetailRow(
                        icon = Icons.Default.Person,
                        label = "Vice Principal",
                        value = repository.vicePrincipalName,
                        highlight = true
                    )

                    ContactDetailRow(
                        icon = Icons.Default.Phone,
                        label = "Contact Number",
                        value = repository.schoolPhone,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${repository.schoolPhone}"))
                            context.startActivity(intent)
                        }
                    )

                    ContactDetailRow(
                        icon = Icons.Default.LocationOn,
                        label = "School Address",
                        value = repository.schoolAddress
                    )

                    ContactDetailRow(
                        icon = Icons.Default.AccessTime,
                        label = "Visiting Hours",
                        value = "09:00 AM – 03:30 PM (Mon to Sat)"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons: Direct Call, SMS, WhatsApp
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${repository.schoolPhone}"))
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SchoolNavy,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("contact_dial_button")
                        ) {
                            Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call Now")
                        }

                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse("sms:${repository.schoolPhone}?body=Hello%20Vice%20Principal%2C%20I%20have%20an%20inquiry%20regarding%20UMS%20Nehrua%20Kala.")
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
                                .testTag("contact_sms_button")
                        ) {
                            Icon(imageVector = Icons.Default.Message, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Send SMS")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = {
                            val geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.schoolGeoUri))
                            context.startActivity(geoIntent)
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SchoolNavy)
                    ) {
                        Icon(imageVector = Icons.Default.Navigation, contentDescription = null, tint = SchoolNavy, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Get Directions on Maps", color = SchoolNavy, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // 3. Frequently Asked Questions (FAQ)
        item {
            Text(
                text = "FREQUENTLY ASKED QUESTIONS",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = SchoolBlue,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Helpful Answers for Parents",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily.Serif,
                    color = SchoolNavy
                )
            )
        }

        items(repository.faqs) { faq ->
            FaqAccordionCard(faq = faq)
        }

        // 4. Footer Note
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "UMS Nehrua Kala — Learn • Grow • Achieve",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = SchoolNavy
                        )
                    )
                    Text(
                        text = "Vice Principal: Mr Janardan Ojha | Contact: 9471471275",
                        style = MaterialTheme.typography.labelSmall.copy(color = SchoolMuted)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    highlight: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(if (highlight) Color(0xFFFFF3D0) else Color(0xFFE8EEF7)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (highlight) SchoolGoldDark else SchoolNavy,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = SchoolMuted,
                    fontSize = 11.5.sp
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (highlight) FontWeight.Bold else FontWeight.SemiBold,
                    color = if (onClick != null) SchoolBlue else SchoolNavyDark,
                    fontSize = 13.5.sp
                )
            )
        }
    }
}

@Composable
fun FaqAccordionCard(faq: FaqItem) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5EDF6)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = faq.question,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = SchoolNavyDark
                    ),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = SchoolNavy,
                    modifier = Modifier.size(20.dp)
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = faq.answer,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = SchoolInk,
                            lineHeight = 18.sp
                        )
                    )
                }
            }
        }
    }
}
