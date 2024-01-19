package com.example.skytracker.domain.interacters

import com.example.skytracker.domain.WeatherRepository
import com.example.skytracker.domain.models.Weather
import com.example.skytracker.domain.models.WeatherRes
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun execute(query: String): WeatherRes {
        return weatherRepository.getWeatherData(query)
    }
}