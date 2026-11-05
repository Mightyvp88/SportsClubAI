package com.example.sportsclubai.data.local

import androidx.room.TypeConverter
import com.example.sportsclubai.data.model.EventType
import com.example.sportsclubai.data.model.SkillLevel
import com.example.sportsclubai.data.model.UserRole

class Converters {
    @TypeConverter
    fun fromUserRole(value: UserRole): String = value.name

    @TypeConverter
    fun toUserRole(value: String): UserRole = UserRole.valueOf(value)

    @TypeConverter
    fun fromSkillLevel(value: SkillLevel): String = value.name

    @TypeConverter
    fun toSkillLevel(value: String): SkillLevel = SkillLevel.valueOf(value)

    @TypeConverter
    fun fromEventType(value: EventType): String = value.name

    @TypeConverter
    fun toEventType(value: String): EventType = EventType.valueOf(value)
}
