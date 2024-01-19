package com.example.skytracker.domain.mappres

import com.example.skytracker.data.api.WeatherData
import com.example.skytracker.domain.models.Weather

fun WeatherData.toWeatherDomain(): Weather {

    val temp = main.temp
    val pressure = main.pressure
    val humidity = main.humidity
    val windSpeed = wind.speed
    val date = dt
    val weatherIcon = weather.firstOrNull()?.icon.orEmpty()
    val dt = dt_txt

    return Weather(temp, pressure, humidity, windSpeed, date, weatherIcon, dt)
}
