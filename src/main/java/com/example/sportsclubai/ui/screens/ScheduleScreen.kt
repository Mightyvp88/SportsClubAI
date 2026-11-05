package com.example.sportsclubai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsclubai.data.model.Schedule
import com.example.sportsclubai.ui.viewmodel.SportsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(viewModel: SportsViewModel, onBack: () -> Unit) {
    val schedules by viewModel.schedules.collectAsState()
    val message by viewModel.message.collectAsState()
    val snackbar = remember { SnackbarHostState() }
    var showAdd by remember { mutableStateOf(false) }
    LaunchedEffect(message) { message?.let { snackbar.showSnackbar(it); viewModel.clearMessage() } }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lịch tập luyện") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại") }
        }) },
        snackbarHost = { SnackbarHost(snackbar) },
        floatingActionButton = { FloatingActionButton(onClick = { showAdd = true }) { Icon(Icons.Default.Add, contentDescription = "Thêm buổi tập") } }
    ) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            item { Text("Lịch tập của câu lạc bộ", fontSize = 20.sp, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp)) }
            if (schedules.isEmpty()) item { Text("Chưa có buổi tập nào. Nhấn nút + để tạo lịch tập đầu tiên.", color = MaterialTheme.colorScheme.secondary) }
            items(schedules, key = { it.id }) { schedule ->
                ScheduleItem(schedule = schedule, onCheckIn = { viewModel.checkIn(schedule.id) })
            }
        }
    }
    if (showAdd) AddTrainingDialog(onDismiss = { showAdd = false }, onSave = { title, place, note ->
        viewModel.addTraining(title, place, note); showAdd = false
    })
}

@Composable
private fun ScheduleItem(schedule: Schedule, onCheckIn: () -> Unit) {
    Card(Modifier.fillMaxWidth().padding(vertical = 7.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(schedule.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale("vi", "VN")).format(Date(schedule.startTime)), color = MaterialTheme.colorScheme.primary)
            Text("Địa điểm: ${schedule.location}")
            if (schedule.description.isNotBlank()) Text(schedule.description, style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(8.dp))
            Button(onClick = onCheckIn, modifier = Modifier.align(androidx.compose.ui.Alignment.End)) { Text("Check-in") }
        }
    }
}

@Composable
private fun AddTrainingDialog(onDismiss: () -> Unit, onSave: (String, String, String) -> Unit) {
    var title by remember { mutableStateOf("Buổi tập Pickleball") }
    var place by remember { mutableStateOf("Sân chính") }
    var note by remember { mutableStateOf("") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("Thêm buổi tập") }, text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(title, { title = it }, label = { Text("Tên buổi tập") }, singleLine = true)
            OutlinedTextField(place, { place = it }, label = { Text("Địa điểm") }, singleLine = true)
            OutlinedTextField(note, { note = it }, label = { Text("Ghi chú") })
            Text("Thời gian được ghi nhận là thời điểm tạo lịch.", style = MaterialTheme.typography.bodySmall)
        }
    }, confirmButton = { TextButton(onClick = { onSave(title, place, note) }) { Text("Lưu") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("Hủy") } })
}
