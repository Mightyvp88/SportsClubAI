package com.example.sportsclubai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_results")
data class MatchResult(
    @PrimaryKey val id: String,
    val scheduleId: String,
    val teamAId: String,
    val teamBId: String,
    val scoreA: Int,
    val scoreB: Int,
    val winnerTeamId: String?,
    val date: Long
)

@Entity(tableName = "player_stats")
data class PlayerStats(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val matchId: String,
    val points: Int,
    val assists: Int? = null,
    val errors: Int? = null
)
