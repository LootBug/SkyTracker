package com.example.rick_and_morty_characters_wiki.di

import android.content.Context
import androidx.room.Room
import com.example.skytracker.data.WeatherRepositoryImpl
import com.example.skytracker.data.api.ApiService
import com.example.skytracker.data.api.Instance
import com.example.skytracker.data.database.LastCityDatabase
import com.example.skytracker.domain.WeatherRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(apiService: ApiService, @ApplicationContext applicationContext: Context): WeatherRepository {
        return WeatherRepositoryImpl(apiService, applicationContext)
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Instance.api
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LastCityDatabase {
        return LastCityDatabase.getDatabase(context)
    }

}