package com.example.sportsclubai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsclubai.ai.AIService
import com.example.sportsclubai.data.model.*
import com.example.sportsclubai.data.repository.SportsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID

class SportsViewModel(
    private val repository: SportsRepository,
    private val aiService: AIService
) : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val chatMessages: StateFlow<List<Pair<String, Boolean>>> = _chatMessages.asStateFlow()
    private val _chatLoading = MutableStateFlow(false)
    val chatLoading: StateFlow<Boolean> = _chatLoading.asStateFlow()
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    private val _performanceAnalysis = MutableStateFlow("")
    val performanceAnalysis: StateFlow<String> = _performanceAnalysis.asStateFlow()
    private val _recommendations = MutableStateFlow<List<String>>(emptyList())
    val recommendations: StateFlow<List<String>> = _recommendations.asStateFlow()

    val users = repository.getAllUsers().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val matchResults = repository.getAllMatchResults().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val schedules = repository.getSchedulesByClub(DEFAULT_CLUB_ID).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun login(email: String) {
        viewModelScope.launch {
            val existing = repository.getUserById(DEFAULT_USER_ID)
            val user = existing ?: User(DEFAULT_USER_ID, email.substringBefore('@').ifBlank { "Thành viên" }, email,
                UserRole.ADMIN, 23, null, SkillLevel.INTERMEDIATE, "")
            repository.insertUser(user)
            _currentUser.value = user
            _recommendations.value = aiService.getRecommendations(user)
        }
    }

    fun addTraining(title: String, location: String, description: String) {
        if (title.isBlank() || location.isBlank()) { _message.value = "Vui lòng nhập tên buổi tập và địa điểm."; return }
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            repository.insertSchedule(Schedule(UUID.randomUUID().toString(), DEFAULT_CLUB_ID, null,
                title.trim(), description.trim(), now, now + 60 * 60 * 1000L, EventType.TRAINING, location.trim()))
            _message.value = "Đã thêm buổi tập."
        }
    }

    fun checkIn(scheduleId: String) {
        viewModelScope.launch {
            val userId = _currentUser.value?.id ?: DEFAULT_USER_ID
            val already = repository.getAttendance(scheduleId, userId)
            if (already?.isPresent == true) { _message.value = "Bạn đã điểm danh buổi này rồi."; return@launch }
            repository.insertAttendance(Attendance(scheduleId, userId, true, System.currentTimeMillis()))
            _message.value = "Check-in thành công!"
        }
    }

    fun addMatch(teamA: String, teamB: String, scoreA: Int, scoreB: Int) {
        if (teamA.isBlank() || teamB.isBlank() || teamA.equals(teamB, true)) {
            _message.value = "Vui lòng nhập hai đội khác nhau."; return
        }
        if (scoreA < 0 || scoreB < 0) { _message.value = "Tỷ số không được âm."; return }
        viewModelScope.launch {
            val winner = when { scoreA > scoreB -> teamA.trim(); scoreB > scoreA -> teamB.trim(); else -> null }
            repository.insertMatchResult(MatchResult(UUID.randomUUID().toString(), "manual", teamA.trim(), teamB.trim(),
                scoreA, scoreB, winner, System.currentTimeMillis()))
            _message.value = "Đã lưu kết quả trận đấu."
        }
    }

    fun clearMessage() { _message.value = null }

    fun sendMessage(text: String) {
        if (text.isBlank() || _chatLoading.value) return
        _chatMessages.update { it + (text.trim() to true) }
        viewModelScope.launch {
            _chatLoading.value = true
            try {
                val response = aiService.getChatResponse(text.trim())
                _chatMessages.update { it + (response to false) }
            } catch (e: Exception) {
                _chatMessages.update { it + ("Hiện chưa thể tạo câu trả lời: ${e.localizedMessage ?: "lỗi không xác định"}" to false) }
            } finally { _chatLoading.value = false }
        }
    }

    fun analyzePerformance() {
        viewModelScope.launch {
            val results = matchResults.value
            _performanceAnalysis.value = aiService.analyzePerformance(results, emptyList())
        }
    }

    companion object {
        const val DEFAULT_USER_ID = "1"
        const val DEFAULT_CLUB_ID = "default-club"
    }
}
