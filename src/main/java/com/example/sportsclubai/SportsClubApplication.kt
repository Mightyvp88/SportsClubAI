package com.example.sportsclubai

import android.app.Application
import androidx.room.Room
import com.example.sportsclubai.ai.AIService
import com.example.sportsclubai.data.local.AppDatabase
import com.example.sportsclubai.data.repository.SportsRepository

class SportsClubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        installAppCheckProvider()
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "sports_club_db"
        ).build()
    }

    val repository by lazy {
        SportsRepository(database.sportsClubDao())
    }

    val aiService by lazy {
        AIService()
    }
}