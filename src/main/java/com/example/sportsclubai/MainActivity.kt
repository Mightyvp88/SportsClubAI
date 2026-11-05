package com.example.sportsclubai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportsclubai.ui.navigation.Screen
import com.example.sportsclubai.ui.screens.*
import com.example.sportsclubai.ui.theme.SportsClubAITheme
import com.example.sportsclubai.ui.viewmodel.SportsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val app = application as SportsClubApplication
        val viewModelFactory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SportsViewModel(app.repository, app.aiService) as T
            }
        }

        setContent {
            SportsClubAIApp(viewModelFactory)
        }
    }
}

@Composable
fun SportsClubAIApp(viewModelFactory: ViewModelProvider.Factory) {
    SportsClubAITheme {
        val navController = rememberNavController()
        val viewModel: SportsViewModel = viewModel(factory = viewModelFactory)

        NavHost(navController = navController, startDestination = Screen.Login.route) {
            composable(Screen.Login.route) {
                LoginScreen(viewModel) {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }
            composable(Screen.Dashboard.route) {
                DashboardScreen(viewModel) { route ->
                    navController.navigate(route)
                }
            }
            composable(Screen.Schedule.route) {
                ScheduleScreen(viewModel) { navController.popBackStack() }
            }
            composable(Screen.MatchResults.route) {
                MatchResultsScreen(viewModel) { navController.popBackStack() }
            }
            composable(Screen.AIChat.route) {
                AIChatScreen(viewModel) { navController.popBackStack() }
            }
            composable(Screen.Performance.route) {
                PerformanceScreen(viewModel) { navController.popBackStack() }
            }
            composable(Screen.Profile.route) {
                ProfileScreen(viewModel) { navController.popBackStack() }
            }
            composable(Screen.ClubManagement.route) {
                ClubManagementScreen(viewModel) { navController.popBackStack() }
            }
        }
    }
}
