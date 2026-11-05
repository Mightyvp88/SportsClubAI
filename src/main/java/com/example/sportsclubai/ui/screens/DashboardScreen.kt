package com.example.sportsclubai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsclubai.data.model.UserRole
import com.example.sportsclubai.ui.viewmodel.SportsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: SportsViewModel, onNavigate: (String) -> Unit) {
    val user by viewModel.currentUser.collectAsState()
    val recommendations by viewModel.recommendations.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("SportsClub AI") })
        },
        bottomBar = {
            BottomNavigationBar(onNavigate)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Welcome back, ${user?.name ?: "Athlete"}!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Sport: Pickleball | Level: ${user?.skillLevel}", color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionTitle("AI Recommendations")
                recommendations.forEach { recommendation ->
                    RecommendationCard(recommendation)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionTitle("Quick Actions")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ActionCard("Schedule", Icons.Default.CalendarToday, Modifier.weight(1f)) { onNavigate("schedule") }
                    ActionCard("Matches", Icons.Default.SportsScore, Modifier.weight(1f)) { onNavigate("match_results") }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ActionCard("AI Coach", Icons.Default.Chat, Modifier.weight(1f)) { onNavigate("ai_chat") }
                    ActionCard("Analysis", Icons.Default.BarChart, Modifier.weight(1f)) { onNavigate("performance") }
                }

                if (user?.role == UserRole.ADMIN || user?.role == UserRole.COACH) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ActionCard("Club Management", Icons.Default.Settings, Modifier.fillMaxWidth()) { onNavigate("club_management") }
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun RecommendationCard(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text)
        }
    }
}

@Composable
fun ActionCard(title: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null)
            Text(text = title, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun BottomNavigationBar(onNavigate: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { onNavigate("dashboard") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Chat, contentDescription = "AI") },
            label = { Text("AI Coach") },
            selected = false,
            onClick = { onNavigate("ai_chat") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { onNavigate("profile") }
        )
    }
}
