package com.example.sportsclubai.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object ClubManagement : Screen("club_management")
    object Schedule : Screen("schedule")
    object MatchResults : Screen("match_results")
    object AIChat : Screen("ai_chat")
    object Performance : Screen("performance")
    object Profile : Screen("profile")
}
