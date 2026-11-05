package com.example.sportsclubai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

enum class EventType {
    TRAINING, MATCH, EVENT
}

@Entity(tableName = "schedules")
data class Schedule(
    @PrimaryKey val id: String,
    val clubId: String,
    val teamId: String?,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val type: EventType,
    val location: String
)

@Entity(tableName = "attendance", primaryKeys = ["scheduleId", "userId"])
data class Attendance(
    val scheduleId: String,
    val userId: String,
    val isPresent: Boolean,
    val timestamp: Long
)
