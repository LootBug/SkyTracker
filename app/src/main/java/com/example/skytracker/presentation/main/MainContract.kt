package com.example.skytracker.presentation.main

import com.example.skytracker.domain.models.Weather
import com.example.skytracker.domain.models.WeatherRes

class MainContract {
    interface View {
        fun showWeather(weather: WeatherRes)
    }

    interface Presenter {
        suspend fun onViewCreated()
        fun onCitySelected(city: String)
    }
}