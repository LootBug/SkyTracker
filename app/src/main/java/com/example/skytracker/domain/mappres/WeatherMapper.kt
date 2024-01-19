package com.example.skytracker.domain.mappres

import com.example.skytracker.data.api.WeatherData
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.domain.models.Weather
import com.example.skytracker.domain.models.WeatherRes

fun WeatherResponse.toWeatherDomain(): WeatherRes {
    val weather = list

    return WeatherRes(weather = weather.map { it.toWeatherModel() }, city = city.name, timezone = city.timezone)
}

fun WeatherData.toWeatherModel(): Weather{
    // Extracting required fields from WeatherData
    val temp = main.temp
    val pressure = main.pressure
    val humidity = main.humidity
    val windSpeed = wind.speed
    val date = dt
    val weatherIcon = weather.firstOrNull()?.icon.orEmpty()
    val dt = dt_txt

    // Creating a WeatherModel object
    return Weather(temp, pressure, humidity, windSpeed, date, weatherIcon, dt)
}
