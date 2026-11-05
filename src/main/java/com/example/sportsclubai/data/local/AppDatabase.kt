package com.example.sportsclubai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportsclubai.data.model.*

@Database(
    entities = [
        User::class,
        Club::class,
        Team::class,
        TeamMember::class,
        Schedule::class,
        Attendance::class,
        MatchResult::class,
        PlayerStats::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportsClubDao(): SportsClubDao
}
