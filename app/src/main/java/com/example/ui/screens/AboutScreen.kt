package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.SchoolBlue
import com.example.ui.theme.SchoolGold
import com.example.ui.theme.SchoolGoldDark
import com.example.ui.theme.SchoolMuted
import com.example.ui.theme.SchoolNavy
import com.example.ui.theme.SchoolNavyDark
import com.example.ui.theme.SchoolPale
import com.example.ui.viewmodel.SchoolNavTab
import com.example.ui.viewmodel.SchoolViewModel

@Composable
fun AboutScreen(
    viewModel: SchoolViewModel,
    onNavigateTab: (SchoolNavTab) -> Unit,
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
        // 1. Header Banner Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SchoolNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "ABOUT OUR SCHOOL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Helping Every Student Move Forward",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "UMS Nehrua Kala, located at Nehrua Kala, Panchdeori, Gopalganj, is committed to providing quality education with moral values and a supportive learning environment. Our aim is to nurture young minds and help them achieve their dreams.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFDCE7F5),
                            lineHeight = 22.sp
                        )
                    )
                }
            }
        }

        // 2. School Photo showcase
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.img_school_hero),
                        contentDescription = "UMS Nehrua Kala campus ground",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                    )
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = SchoolNavy,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Nehrua Kala, Block: Panchdeori, Dist: Gopalganj (Bihar)",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = SchoolNavy
                            )
                        )
                    }
                }
            }
        }

        // 3. Message from the Vice Principal Desk
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2EAF4))
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(SchoolNavyDark)
                                .border(2.dp, SchoolGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = SchoolGold,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = repository.vicePrincipalName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    color = SchoolNavy
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

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SchoolPale, RoundedCornerShape(10.dp))
                            .padding(14.dp)
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.FormatQuote,
                                contentDescription = null,
                                tint = SchoolGoldDark,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "\"At UMS Nehrua Kala, our supreme priority is ensuring every child from rural Panchdeori receives equal educational opportunities, nutritious daily meals, and respectful guidance. We welcome every family into our learning family.\"",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = FontFamily.Serif,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    color = SchoolNavyDark,
                                    lineHeight = 22.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${repository.schoolPhone}"))
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = SchoolNavy),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Connect with Vice Principal (${repository.schoolPhone})")
                    }
                }
            }
        }

        // 4. Campus Facilities & Key Features
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        text = "CAMPUS INFRASTRUCTURE",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Facilities for Student Well-Being",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Serif,
                            color = SchoolNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FacilityRow(
                        icon = Icons.Default.MenuBook,
                        title = "Free Textbooks & Reading Books",
                        desc = "State board curriculum books supplied to every enrolled boy and girl."
                    )
                    FacilityRow(
                        icon = Icons.Default.WaterDrop,
                        title = "Clean Drinking Water & Sanitation",
                        desc = "Safe purified tap water points and dedicated hygiene facilities."
                    )
                    FacilityRow(
                        icon = Icons.Default.School,
                        title = "Activity-Based Classrooms",
                        desc = "Blackboards, educational charts, and foundational learning kits."
                    )
                    FacilityRow(
                        icon = Icons.Default.SportsHandball,
                        title = "Sports & Physical Exercises",
                        desc = "Morning assembly, yoga, football, kabaddi, and athletic games."
                    )
                }
            }
        }
    }
}

@Composable
fun FacilityRow(
    icon: ImageVector,
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8EEF7)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SchoolNavy,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = SchoolNavyDark
                )
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = SchoolMuted,
                    lineHeight = 16.sp
                )
            )
        }
    }
}
