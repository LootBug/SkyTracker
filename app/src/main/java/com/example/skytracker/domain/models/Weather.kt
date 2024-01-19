package com.example.skytracker.domain.models


data class WeatherRes(
    val city: String,
    val timezone: Int,
    val weather: List<Weather>
)
data class Weather(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val date: Long,
    val weatherIcon: String,
    val dt: String
)

