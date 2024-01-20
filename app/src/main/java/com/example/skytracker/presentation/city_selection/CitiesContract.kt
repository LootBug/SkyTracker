package com.example.skytracker.presentation.city_selection

import com.example.skytracker.domain.models.City

interface CitiesContract {
    interface View {
        fun showCities(cityList: List<City>)
    }

    interface Presenter {
        fun onViewCreated()
    }
}