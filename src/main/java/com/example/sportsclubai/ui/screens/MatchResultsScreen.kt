package com.example.sportsclubai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsclubai.data.model.MatchResult
import com.example.sportsclubai.ui.viewmodel.SportsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchResultsScreen(viewModel: SportsViewModel, onBack: () -> Unit) {
    val results by viewModel.matchResults.collectAsState()
    val message by viewModel.message.collectAsState()
    val snackbar = remember { SnackbarHostState() }
    var showAdd by remember { mutableStateOf(false) }
    LaunchedEffect(message) { message?.let { snackbar.showSnackbar(it); viewModel.clearMessage() } }
    Scaffold(topBar = { TopAppBar(title = { Text("Kết quả trận đấu") }, navigationIcon = {
        IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại") }
    }) }, snackbarHost = { SnackbarHost(snackbar) }, floatingActionButton = {
        FloatingActionButton(onClick = { showAdd = true }) { Icon(Icons.Default.Add, contentDescription = "Thêm trận") }
    }) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            if (results.isEmpty()) item { Text("Chưa có kết quả. Nhấn + để nhập trận đấu đầu tiên.", color = MaterialTheme.colorScheme.secondary) }
            items(results, key = { it.id }) { MatchResultItem(it) }
        }
    }
    if (showAdd) AddMatchDialog(onDismiss = { showAdd = false }, onSave = { a, b, sa, sb ->
        viewModel.addMatch(a, b, sa, sb); showAdd = false
    })
}

@Composable
fun MatchResultItem(result: MatchResult) {
    Card(Modifier.fillMaxWidth().padding(vertical = 7.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Text(result.teamAId, fontWeight = FontWeight.Bold)
                    Text(result.scoreA.toString(), fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                }
                Text("VS", fontWeight = FontWeight.Light)
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Text(result.teamBId, fontWeight = FontWeight.Bold)
                    Text(result.scoreB.toString(), fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
            Text("${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(result.date))} • ${result.winnerTeamId?.let { "Thắng: $it" } ?: "Hòa"}", modifier = Modifier.align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun AddMatchDialog(onDismiss: () -> Unit, onSave: (String, String, Int, Int) -> Unit) {
    var teamA by remember { mutableStateOf("Đội A") }; var teamB by remember { mutableStateOf("Đội B") }
    var scoreA by remember { mutableStateOf("11") }; var scoreB by remember { mutableStateOf("8") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("Nhập kết quả trận đấu") }, text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(teamA, { teamA = it }, label = { Text("Tên đội A") }, singleLine = true)
            OutlinedTextField(teamB, { teamB = it }, label = { Text("Tên đội B") }, singleLine = true)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(scoreA, { scoreA = it.filter(Char::isDigit) }, label = { Text("Điểm đội A") }, modifier = Modifier.weight(1f), singleLine = true)
                OutlinedTextField(scoreB, { scoreB = it.filter(Char::isDigit) }, label = { Text("Điểm đội B") }, modifier = Modifier.weight(1f), singleLine = true)
            }
        }
    }, confirmButton = { TextButton(onClick = { onSave(teamA, teamB, scoreA.toIntOrNull() ?: -1, scoreB.toIntOrNull() ?: -1) }) { Text("Lưu kết quả") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("Hủy") } })
}
