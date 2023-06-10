package com.example.skytracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_city")
data class LastCityEntity(
    @PrimaryKey val id: Int = 0,
    val lastCity: String,
)