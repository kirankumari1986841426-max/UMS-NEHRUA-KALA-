package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.SchoolValue
import com.example.ui.theme.SchoolBlue
import com.example.ui.theme.SchoolGold
import com.example.ui.theme.SchoolGoldDark
import com.example.ui.theme.SchoolInk
import com.example.ui.theme.SchoolMuted
import com.example.ui.theme.SchoolNavy
import com.example.ui.theme.SchoolNavyDark
import com.example.ui.theme.SchoolPale
import com.example.ui.viewmodel.SchoolNavTab
import com.example.ui.viewmodel.SchoolViewModel

@Composable
fun HomeScreen(
    viewModel: SchoolViewModel,
    onNavigateTab: (SchoolNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = viewModel.repository
    val readinessPct = viewModel.getReadinessPercentage()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolPale),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // 1. Hero Section with school photo & tagline
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(290.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_school_hero),
                    contentDescription = "UMS Nehrua Kala School Campus",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Elegant gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0xAA08254D),
                                    SchoolNavyDark
                                ),
                                startY = 60f
                            )
                        )
                )

                // Hero Copy content
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(18.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SchoolGold)
                            .padding(horizontal = 10.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "WELCOME TO UMS NEHRUA KALA",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SchoolNavyDark,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.2.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Education Today…\nA Better Tomorrow",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 30.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Knowledge, confidence and good values help every child take a step towards a brighter future.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFFE3EDF8),
                            lineHeight = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onNavigateTab(SchoolNavTab.ADMISSIONS) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SchoolGold,
                                contentColor = SchoolNavyDark
                            ),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                            modifier = Modifier.testTag("hero_admission_button")
                        ) {
                            Text(
                                text = "Admissions Info",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${repository.schoolPhone}"))
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.White
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                            modifier = Modifier.testTag("hero_call_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = SchoolGold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Call School",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }

        // 2. Urgent / Active Notice Ticker
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable { onNavigateTab(SchoolNavTab.NOTICES) }
                    .testTag("home_notice_ticker"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF9E6)
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFD56B))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SchoolGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "📢", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "New Session Admissions Open (2025–26)",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = SchoolNavyDark
                            )
                        )
                        Text(
                            text = "Visit campus 9:30 AM – 1:30 PM with child & parent Aadhaar and bank details.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF5D4037)
                            ),
                            maxLines = 1
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "View notice",
                        tint = SchoolGoldDark,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // 3. School Values (4 Pillars)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "OUR CORE VALUES",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = SchoolGoldDark,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = "Building a Foundation for Success",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Serif,
                        color = SchoolNavy
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val values = repository.values
                    for (i in values.indices step 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ValueCard(
                                value = values[i],
                                modifier = Modifier.weight(1f)
                            )
                            if (i + 1 < values.size) {
                                ValueCard(
                                    value = values[i + 1],
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // 4. Document Readiness Checklist Snapshot
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .testTag("readiness_snapshot_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "ADMISSION FORMALITIES",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = SchoolBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Document Readiness",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontFamily = FontFamily.Serif,
                                    color = SchoolNavy
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (readinessPct == 100) Color(0xFFE8F5E9) else Color(0xFFFFF8E1))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$readinessPct% Ready",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (readinessPct == 100) Color(0xFF2E7D32) else Color(0xFFF57F17)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { readinessPct / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = SchoolGold,
                        trackColor = Color(0xFFE2EAF4)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Prepare Aadhaar cards, student bank account, recent photo, and previous school TC before visiting the admission counter.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = SchoolMuted,
                            lineHeight = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { onNavigateTab(SchoolNavTab.ADMISSIONS) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SchoolNavy,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Open Full Document Checklist & Form",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.5.sp
                        )
                    }
                }
            }
        }

        // 5. Leadership & Contact Snapshot
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        text = "SCHOOL ADMINISTRATION",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Administration & Leadership",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Serif,
                            color = SchoolNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8EEF7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = SchoolNavy,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = repository.vicePrincipalName,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SchoolNavyDark
                                )
                            )
                            Text(
                                text = "Vice Principal, UMS Nehrua Kala",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = SchoolMuted
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${repository.schoolPhone}"))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SchoolNavy)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = null,
                                tint = SchoolNavy,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Call 9471471275",
                                color = SchoolNavy,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = { onNavigateTab(SchoolNavTab.CONTACT) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SchoolNavy
                            )
                        ) {
                            Text(
                                text = "School Address",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // 6. Classroom and Student Life Highlight
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.img_students_learning),
                        contentDescription = "Students learning in classroom",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                    )

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "NURTURING YOUNG MINDS",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SchoolGoldDark,
                                fontWeight = FontWeight.Black
                            )
                        )
                        Text(
                            text = "Interactive & Joyful Learning",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily.Serif,
                                color = SchoolNavy
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Our dedicated educators ensure students develop foundational math, science, and languages through participatory classroom sessions and extracurriculars.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = SchoolMuted,
                                lineHeight = 18.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Explore Academics & MDM Routine →",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = SchoolBlue,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.clickable { onNavigateTab(SchoolNavTab.ACADEMICS) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ValueCard(
    value: SchoolValue,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5EDF6))
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(text = value.iconEmoji, fontSize = 26.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = SchoolNavy
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = SchoolMuted,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            )
        }
    }
}
