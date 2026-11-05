package com.example.sportsclubai.data.local

import androidx.room.*
import com.example.sportsclubai.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SportsClubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): User?

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClub(club: Club)

    @Query("SELECT * FROM clubs WHERE id = :clubId")
    suspend fun getClubById(clubId: String): Club?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Query("SELECT * FROM teams WHERE clubId = :clubId")
    fun getTeamsByClub(clubId: String): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    @Query("SELECT * FROM schedules WHERE clubId = :clubId ORDER BY startTime DESC")
    fun getSchedulesByClub(clubId: String): Flow<List<Schedule>>

    @Query("SELECT * FROM attendance WHERE scheduleId = :scheduleId AND userId = :userId LIMIT 1")
    suspend fun getAttendance(scheduleId: String, userId: String): Attendance?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: Attendance)

    @Query("SELECT * FROM attendance WHERE scheduleId = :scheduleId")
    fun getAttendanceBySchedule(scheduleId: String): Flow<List<Attendance>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchResult(matchResult: MatchResult)

    @Query("SELECT * FROM match_results ORDER BY date DESC")
    fun getAllMatchResults(): Flow<List<MatchResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerStats(stats: PlayerStats)

    @Query("SELECT * FROM player_stats WHERE userId = :userId")
    fun getStatsByUserId(userId: String): Flow<List<PlayerStats>>
}
