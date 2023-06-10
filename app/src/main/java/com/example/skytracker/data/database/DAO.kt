package com.example.skytracker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LastCityDao {
    @Query("SELECT * FROM last_city")
    fun getLastCity(): List<LastCityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastCity(lastCity: LastCityEntity)
}