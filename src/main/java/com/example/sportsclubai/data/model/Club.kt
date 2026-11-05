package com.example.sportsclubai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clubs")
data class Club(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val location: String
)

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey val id: String,
    val clubId: String,
    val name: String,
    val coachId: String
)

@Entity(tableName = "team_members", primaryKeys = ["teamId", "userId"])
data class TeamMember(
    val teamId: String,
    val userId: String
)
