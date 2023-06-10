package com.example.skytracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LastCityEntity::class], version = 1)
abstract class LastCityDatabase : RoomDatabase() {
    abstract fun lastCityDao(): LastCityDao

    companion object {
        @Volatile
        private var INSTANCE: LastCityDatabase? = null

        fun getDatabase(context: Context): LastCityDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LastCityDatabase::class.java,
                    "last_city_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}