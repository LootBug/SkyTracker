package com.example.skytracker.domain

import com.example.skytracker.domain.models.Weather
import com.example.skytracker.domain.models.WeatherRes

interface WeatherRepository {

    suspend fun getWeatherDataInit() : WeatherRes

    suspend fun getWeatherData(query: String) : WeatherRes

}