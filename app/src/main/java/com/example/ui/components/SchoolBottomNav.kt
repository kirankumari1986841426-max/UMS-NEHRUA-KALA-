package com.example.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SchoolGold
import com.example.ui.theme.SchoolMuted
import com.example.ui.theme.SchoolNavy
import com.example.ui.theme.SchoolNavyDark
import com.example.ui.viewmodel.SchoolNavTab

@Composable
fun SchoolBottomNav(
    selectedTab: SchoolNavTab,
    onTabSelected: (SchoolNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .testTag("school_bottom_nav"),
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        SchoolNavTab.entries.forEach { tab ->
            val isSelected = tab == selectedTab

            val (iconSelected, iconUnselected) = when (tab) {
                SchoolNavTab.HOME -> Icons.Filled.Home to Icons.Outlined.Home
                SchoolNavTab.ABOUT -> Icons.Filled.Info to Icons.Outlined.Info
                SchoolNavTab.ACADEMICS -> Icons.Filled.School to Icons.Outlined.School
                SchoolNavTab.ADMISSIONS -> Icons.Filled.Description to Icons.Outlined.Description
                SchoolNavTab.NOTICES -> Icons.Filled.Campaign to Icons.Outlined.Campaign
                SchoolNavTab.CONTACT -> Icons.Filled.Phone to Icons.Outlined.Phone
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) iconSelected else iconUnselected,
                        contentDescription = tab.label
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SchoolNavyDark,
                    selectedTextColor = SchoolNavy,
                    indicatorColor = SchoolGold,
                    unselectedIconColor = SchoolMuted,
                    unselectedTextColor = SchoolMuted
                ),
                modifier = Modifier.testTag("bottom_tab_${tab.name.lowercase()}")
            )
        }
    }
}
