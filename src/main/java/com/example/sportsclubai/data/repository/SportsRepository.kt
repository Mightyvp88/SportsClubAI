package com.example.sportsclubai.data.repository

import com.example.sportsclubai.data.local.SportsClubDao
import com.example.sportsclubai.data.model.*
import kotlinx.coroutines.flow.Flow

class SportsRepository(private val dao: SportsClubDao) {

    fun getAllUsers(): Flow<List<User>> = dao.getAllUsers()

    suspend fun getUserById(userId: String): User? = dao.getUserById(userId)

    suspend fun insertUser(user: User) = dao.insertUser(user)

    fun getTeamsByClub(clubId: String): Flow<List<Team>> = dao.getTeamsByClub(clubId)

    suspend fun insertTeam(team: Team) = dao.insertTeam(team)

    fun getSchedulesByClub(clubId: String): Flow<List<Schedule>> = dao.getSchedulesByClub(clubId)

    suspend fun insertSchedule(schedule: Schedule) = dao.insertSchedule(schedule)

    fun getAttendanceBySchedule(scheduleId: String): Flow<List<Attendance>> = dao.getAttendanceBySchedule(scheduleId)

    suspend fun insertAttendance(attendance: Attendance) = dao.insertAttendance(attendance)

    suspend fun getAttendance(scheduleId: String, userId: String): Attendance? = dao.getAttendance(scheduleId, userId)

    fun getAllMatchResults(): Flow<List<MatchResult>> = dao.getAllMatchResults()

    suspend fun insertMatchResult(matchResult: MatchResult) = dao.insertMatchResult(matchResult)

    fun getStatsByUserId(userId: String): Flow<List<PlayerStats>> = dao.getStatsByUserId(userId)
}
