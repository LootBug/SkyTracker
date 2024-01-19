package com.example.rick_and_morty_characters_wiki.di

import com.example.skytracker.domain.CityRepository
import com.example.skytracker.domain.WeatherRepository
import com.example.skytracker.domain.interacters.GetLastCityUseCase
import com.example.skytracker.domain.interacters.GetWeatherDataInitUseCase
import com.example.skytracker.domain.interacters.GetWeatherDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetWeatherInitUseCase(weatherRepository: WeatherRepository) : GetWeatherDataInitUseCase {
        return GetWeatherDataInitUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun provideGetWeatherUseCase(weatherRepository: WeatherRepository) : GetWeatherDataUseCase {
        return GetWeatherDataUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    suspend fun provideGetLastCityUseCase(cityRepository: CityRepository) : String? {
        return GetLastCityUseCase(cityRepository).execute()
    }

}