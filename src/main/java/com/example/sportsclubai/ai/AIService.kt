
package com.example.sportsclubai.ai

import android.util.Log
import com.example.sportsclubai.data.model.*
import com.google.firebase.Firebase
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.ai
import kotlinx.coroutines.CancellationException

class AIService {

    suspend fun getChatResponse(query: String): String {
        val question = query.trim()

        if (question.isBlank()) {
            return "Bạn hãy nhập câu hỏi trước nhé."
        }

        return try {
            Log.d("AIService", "Bắt đầu gửi câu hỏi đến Gemini")

            val model = Firebase.ai(
                backend = GenerativeBackend.googleAI(),
                useLimitedUseAppCheckTokens = true
            ).generativeModel("gemini-3.8-flash")

            val prompt = """
                Bạn là trợ lý AI của ứng dụng SportsClub AI.

                Trả lời bằng tiếng Việt, tự nhiên, rõ ràng,
                có cấu trúc và đúng trọng tâm.

                Bạn có chuyên môn về Pickleball, huấn luyện
                thể thao, lịch tập, chiến thuật, thể lực và
                quản lý câu lạc bộ.

                Tuy nhiên, không được từ chối chỉ vì câu hỏi
                nằm ngoài thể thao hoặc Pickleball.

                Hãy trả lời cả câu hỏi phổ thông, học tập,
                công nghệ và đời sống nếu phù hợp.

                Không bịa dữ kiện. Nếu thiếu thông tin hoặc
                không chắc chắn, hãy nói rõ.

                Câu hỏi của người dùng:
                $question
            """.trimIndent()

            // Chờ phản hồi, không đặt timeout trong hàm này
            val result = model.generateContent(prompt)

            val answer = result.text?.takeIf {
                it.isNotBlank()
            }

            if (answer != null) {
                Log.d("AIService", "Gemini đã trả lời thành công")
                answer
            } else {
                "Gemini chưa trả về nội dung. Bạn hãy thử lại."
            }

        } catch (cancelled: CancellationException) {
            throw cancelled

        } catch (error: Exception) {
            Log.e("AIService", "Lỗi khi gọi Gemini", error)

            val detail = error.localizedMessage
                ?.takeIf { it.isNotBlank() }
                ?: error.javaClass.simpleName

            "Không thể nhận phản hồi từ Gemini.\n\n" +
                    "Chi tiết lỗi: $detail\n\n" +
                    "Hãy kiểm tra Internet, Firebase AI Logic " +
                    "và App Check rồi thử lại."
        }
    }

    suspend fun analyzePerformance(
        results: List<MatchResult>,
        stats: List<PlayerStats>
    ): String {
        if (results.isEmpty()) {
            return "Chưa có trận đấu nào được lưu. Hãy thêm kết quả trận đấu để bắt đầu phân tích."
        }

        val decided = results.count { it.winnerTeamId != null }
        val draws = results.size - decided
        val aWins = results.count { it.winnerTeamId == it.teamAId }
        val bWins = results.count { it.winnerTeamId == it.teamBId }

        return "Đã ghi nhận ${results.size} trận: $decided trận có đội thắng và $draws trận hòa. " +
                "Đội A thắng $aWins trận, Đội B thắng $bWins trận. " +
                "Đây là thống kê toàn bộ trận, chưa đại diện cho tỷ lệ thắng cá nhân."
    }

    suspend fun getRecommendations(user: User): List<String> =
        when (user.skillLevel) {
            SkillLevel.BEGINNER -> listOf(
                "Tập giao bóng và trả giao bóng ổn định",
                "Luyện dink chéo sân 15 phút",
                "Học vị trí đứng và luật khu vực bếp"
            )

            SkillLevel.INTERMEDIATE -> listOf(
                "Luyện third-shot drop từ cuối sân",
                "Tập volley và reset bóng ở vạch bếp",
                "Cải thiện di chuyển đồng bộ khi đánh đôi"
            )

            SkillLevel.ADVANCED, SkillLevel.PRO -> listOf(
                "Tập chuyển đổi tấn công – phòng thủ",
                "Luyện chiến thuật đánh vào khoảng trống",
                "Mô phỏng tình huống thi đấu áp lực"
            )
        }
}
