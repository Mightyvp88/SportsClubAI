package com.example.sportsclubai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserRole {
    ADMIN, COACH, MEMBER
}

enum class SkillLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, PRO
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val age: Int? = null,
    val gender: String? = null,
    val skillLevel: SkillLevel = SkillLevel.BEGINNER,
    val playingPosition: String? = null
)
