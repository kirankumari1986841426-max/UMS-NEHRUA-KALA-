package com.example.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GradeInfo
import com.example.data.model.MdmMenuItem
import com.example.ui.theme.SchoolBlue
import com.example.ui.theme.SchoolGold
import com.example.ui.theme.SchoolGoldDark
import com.example.ui.theme.SchoolMuted
import com.example.ui.theme.SchoolNavy
import com.example.ui.theme.SchoolNavyDark
import com.example.ui.theme.SchoolPale
import com.example.ui.viewmodel.SchoolViewModel

@Composable
fun AcademicsScreen(
    viewModel: SchoolViewModel,
    modifier: Modifier = Modifier
) {
    val repository = viewModel.repository
    var selectedSection by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Classes & Subjects", "School Timetable", "Mid-Day Meal (MDM)", "Govt Welfare Schemes")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolPale)
    ) {
        // Tab Row Header
        ScrollableTabRow(
            selectedTabIndex = selectedSection,
            containerColor = SchoolNavy,
            contentColor = Color.White,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSection]),
                    color = SchoolGold,
                    height = 3.dp
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedSection == index,
                    onClick = { selectedSection = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 13.sp,
                            fontWeight = if (selectedSection == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedSection == index) SchoolGold else Color(0xFFD4E3F7)
                        )
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            when (selectedSection) {
                0 -> {
                    // Classes & Curriculum
                    item {
                        Text(
                            text = "ACADEMIC WINGS",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SchoolBlue,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Curriculum & Class Structure",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif,
                                color = SchoolNavy
                            )
                        )
                    }

                    items(repository.grades) { grade ->
                        GradeCard(grade = grade)
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2EAF4))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Teaching Methodology",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        color = SchoolNavy
                                    )
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Classes follow the State Board (SCERT / NCERT) curriculum with Hindi and English as core languages, paired with hands-on mathematics and environmental science experiments designed to build practical intuition.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = SchoolMuted,
                                        lineHeight = 18.sp
                                    )
                                )
                            }
                        }
                    }
                }

                1 -> {
                    // School Daily Schedule / Timetable
                    item {
                        Text(
                            text = "DAILY SCHEDULE",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SchoolBlue,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "School Bell Timings",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif,
                                color = SchoolNavy
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "School working hours are Monday to Saturday. Gates open at 8:45 AM.",
                            style = MaterialTheme.typography.bodySmall.copy(color = SchoolMuted)
                        )
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                TimetableRow("09:00 AM - 09:20 AM", "Morning Assembly & Prayer", "Prarthana, National Pledge, Thought for the Day & Physical Exercises")
                                TimetableRow("09:20 AM - 10:05 AM", "Period 1: Language Studies", "Hindi / English Grammar and Reading")
                                TimetableRow("10:05 AM - 10:50 AM", "Period 2: Mathematics (Ganit)", "Foundational numeracy and arithmetic practice")
                                TimetableRow("10:50 AM - 11:35 AM", "Period 3: Science & EVS", "Natural sciences, nature observation and charts")
                                TimetableRow("11:35 AM - 12:20 PM", "Period 4: Social Science", "History, geography, civics & local heritage")
                                TimetableRow("12:20 PM - 01:05 PM", "PM-POSHAN Mid-Day Meal Recess", "Handwashing, hot fresh lunch, supervised recreation", isHighlight = true)
                                TimetableRow("01:05 PM - 01:50 PM", "Period 5: Sanskrit / Urdu", "Second language literature and poetry")
                                TimetableRow("01:50 PM - 02:35 PM", "Period 6: Arts & Craft / Library", "Creative drawing, clay modeling, reading corner")
                                TimetableRow("02:35 PM - 03:15 PM", "Period 7: Sports & Physical Education", "Kho-kho, kabaddi, skipping, athletic training")
                                TimetableRow("03:15 PM - 03:30 PM", "National Anthem & Dismissal", "Jana Gana Mana recitation & orderly dispersal")
                            }
                        }
                    }
                }

                2 -> {
                    // Mid-Day Meal (MDM) / PM-POSHAN Weekly Menu
                    item {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFD56B))
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "🍲", fontSize = 32.sp)
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "PM-POSHAN Mid-Day Meal",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontFamily = FontFamily.Serif,
                                            fontWeight = FontWeight.Bold,
                                            color = SchoolNavyDark
                                        )
                                    )
                                    Text(
                                        text = "Nutritious, warm, freshly cooked meals served daily under hygienic supervision to support every child's growth and health.",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF5D4037),
                                            lineHeight = 16.sp
                                        )
                                    )
                                }
                            }
                        }
                    }

                    items(repository.mdmMenu) { menu ->
                        MdmCard(menu = menu)
                    }
                }

                3 -> {
                    // Government Welfare Schemes
                    item {
                        Text(
                            text = "STUDENT ENTITLEMENTS",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SchoolBlue,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Government Welfare & Benefits",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif,
                                color = SchoolNavy
                            )
                        )
                    }

                    item {
                        WelfareCard(
                            title = "Free Textbooks Scheme",
                            subtitle = "Class 1 to 8",
                            desc = "Complete textbook kits provided free of cost to all admitted students at the start of each academic year.",
                            iconEmoji = "📚"
                        )
                    }

                    item {
                        WelfareCard(
                            title = "Medhasoft DBT Uniform Grant",
                            subtitle = "Direct Bank Transfer to Parents / Students",
                            desc = "Annual uniform fund credited directly into the verified bank account upon meeting attendance criteria (75%+).",
                            iconEmoji = "👕"
                        )
                    }

                    item {
                        WelfareCard(
                            title = "Pre-Matric Scholarship",
                            subtitle = "State Social Welfare Quota",
                            desc = "Financial stipends for eligible SC/ST, EBC, OBC, and minority students through the Bihar State Education Department portal.",
                            iconEmoji = "🎓"
                        )
                    }

                    item {
                        WelfareCard(
                            title = "National De-worming & Health Checkup",
                            subtitle = "Bi-Annual Health Mission",
                            desc = "Routine health screening, iron-folic acid tablets, and medical assessments conducted at school by primary health center doctors.",
                            iconEmoji = "🩺"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GradeCard(grade: GradeInfo) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = grade.standard,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = SchoolNavy
                    )
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE8EEF7))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = grade.ageGroup,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SchoolBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = grade.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = SchoolMuted,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Core Subjects Taught:",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SchoolNavyDark
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    grade.subjects.forEach { subject ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(SchoolGoldDark)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = subject,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = SchoolNavyDark,
                                    fontSize = 12.5.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimetableRow(
    time: String,
    title: String,
    desc: String,
    isHighlight: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (isHighlight) Color(0xFFFFF9E6) else Color.Transparent)
            .padding(vertical = 8.dp, horizontal = if (isHighlight) 10.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isHighlight) SchoolGoldDark else SchoolNavyDark
                )
            )
            Text(
                text = time,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = SchoolMuted,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Text(
            text = desc,
            style = MaterialTheme.typography.bodySmall.copy(
                color = SchoolMuted,
                fontSize = 11.5.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun MdmCard(menu: MdmMenuItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5EDF6))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE8EEF7)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = menu.day.take(3).uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = SchoolNavy
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = menu.mealName,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = SchoolNavy
                    )
                )
                Text(
                    text = menu.details,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SchoolMuted,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "🥗 ${menu.nutritionNotes}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = SchoolGoldDark,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}

@Composable
fun WelfareCard(
    title: String,
    subtitle: String,
    desc: String,
    iconEmoji: String
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = iconEmoji, fontSize = 28.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = SchoolNavy
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = SchoolBlue,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SchoolMuted,
                        lineHeight = 17.sp
                    )
                )
            }
        }
    }
}
