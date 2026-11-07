package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.SchoolBottomNav
import com.example.ui.components.SchoolTopBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AcademicsScreen
import com.example.ui.screens.AdmissionsScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.NoticesScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.SchoolNavTab
import com.example.ui.viewmodel.SchoolViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SchoolApp()
            }
        }
    }
}

@Composable
fun SchoolApp(
    viewModel: SchoolViewModel = viewModel()
) {
    val selectedTab by viewModel.selectedTab.collectAsState()

    // Handle back button: return to Home tab if on another screen
    BackHandler(enabled = selectedTab != SchoolNavTab.HOME) {
        viewModel.selectTab(SchoolNavTab.HOME)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SchoolTopBar(
                onNoticesClick = { viewModel.selectTab(SchoolNavTab.NOTICES) }
            )
        },
        bottomBar = {
            SchoolBottomNav(
                selectedTab = selectedTab,
                onTabSelected = { viewModel.selectTab(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(
                targetState = selectedTab,
                label = "screen_crossfade"
            ) { tab ->
                when (tab) {
                    SchoolNavTab.HOME -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateTab = { viewModel.selectTab(it) }
                    )
                    SchoolNavTab.ABOUT -> AboutScreen(
                        viewModel = viewModel,
                        onNavigateTab = { viewModel.selectTab(it) }
                    )
                    SchoolNavTab.ACADEMICS -> AcademicsScreen(
                        viewModel = viewModel
                    )
                    SchoolNavTab.ADMISSIONS -> AdmissionsScreen(
                        viewModel = viewModel
                    )
                    SchoolNavTab.NOTICES -> NoticesScreen(
                        viewModel = viewModel
                    )
                    SchoolNavTab.CONTACT -> ContactScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
