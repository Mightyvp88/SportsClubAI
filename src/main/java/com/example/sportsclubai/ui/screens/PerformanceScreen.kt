package com.example.sportsclubai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsclubai.ui.viewmodel.SportsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceScreen(viewModel: SportsViewModel, onBack: () -> Unit) {
    val analysis by viewModel.performanceAnalysis.collectAsState()
    val results by viewModel.matchResults.collectAsState()
    val decided = results.count { it.winnerTeamId != null }
    val draws = results.size - decided
    val teamAWins = results.count { it.winnerTeamId == it.teamAId }
    val teamBWins = results.count { it.winnerTeamId == it.teamBId }
    LaunchedEffect(results) { viewModel.analyzePerformance() }
    Scaffold(topBar = { TopAppBar(title = { Text("Thống kê & phân tích") }, navigationIcon = {
        IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại") }
    }, actions = { IconButton(onClick = { viewModel.analyzePerformance() }) { Icon(Icons.Default.Refresh, contentDescription = "Làm mới") } }) }) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.TrendingUp, contentDescription = null); Spacer(Modifier.width(8.dp)); Text("Nhận xét dữ liệu", fontWeight = FontWeight.Bold, fontSize = 18.sp) }
                        Spacer(Modifier.height(8.dp)); Text(analysis.ifBlank { "Đang tổng hợp dữ liệu trận đấu..." })
                    }
                }
            }
            item { Text("Thống kê trận đấu", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            item { StatRow("Tổng số trận đã lưu", results.size.toString()) }
            item { StatRow("Trận có đội thắng", decided.toString()) }
            item { StatRow("Trận hòa", draws.toString()) }
            item { StatRow("Số trận đội A thắng", teamAWins.toString()) }
            item { StatRow("Số trận đội B thắng", teamBWins.toString()) }
            if (results.isEmpty()) item { Text("Chưa có dữ liệu. Hãy vào mục Trận đấu và thêm kết quả để thống kê tự cập nhật.", color = MaterialTheme.colorScheme.secondary) }
            item { Text("Lưu ý: Thống kê này tổng hợp theo tên Đội A/Đội B của từng trận; chưa phải tỷ lệ thắng cá nhân vì ứng dụng chưa gắn người dùng với đội thi đấu.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary) }
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.secondary); Text(value, fontWeight = FontWeight.Bold)
    }
    HorizontalDivider()
}
