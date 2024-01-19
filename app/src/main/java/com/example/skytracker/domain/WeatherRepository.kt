package com.example.skytracker.domain

import com.example.skytracker.domain.models.Weather

interface WeatherRepository {

    suspend fun getWeatherDataInit() : List<Weather>

    suspend fun getWeatherData(query: String) : List<Weather>

}