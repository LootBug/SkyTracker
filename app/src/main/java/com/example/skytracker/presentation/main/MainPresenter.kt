package com.example.skytracker.presentation.main

import com.example.skytracker.domain.interacters.GetLastCityUseCase
import com.example.skytracker.domain.interacters.GetWeatherDataInitUseCase
import com.example.skytracker.domain.interacters.GetWeatherDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val getWeatherDataInitUseCase: GetWeatherDataInitUseCase,
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val getLastCityUseCase: GetLastCityUseCase,
    private val viewInterface: MainContract.View
): MainContract.Presenter {
    override suspend fun onViewCreated() {
        val res = getWeatherDataInitUseCase.execute()
        withContext(Dispatchers.Main) {
            viewInterface.showWeather(res)
        }
    }

    override suspend fun onCitySelected(city: String) {
        val res = getWeatherDataUseCase.execute(city)
        withContext(Dispatchers.Main) {
            viewInterface.showWeather(res)
        }
    }

    override suspend fun getLastCity(): String? {
        return getLastCityUseCase.execute()
    }
}