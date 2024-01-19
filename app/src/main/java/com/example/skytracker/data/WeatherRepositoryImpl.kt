package com.example.skytracker.data

import android.content.Context
import android.widget.Toast
import com.example.skytracker.data.api.ApiService
import com.example.skytracker.data.api.WeatherData
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.domain.WeatherRepository
import com.example.skytracker.domain.mappres.toWeatherDomain
import com.example.skytracker.domain.models.Weather
import com.example.skytracker.domain.models.WeatherRes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext private val applicationContext: Context
) : WeatherRepository {
    override suspend fun getWeatherDataInit(): WeatherRes {
        return fetchWeather(apiService.getWeatherDataInit())
    }

    override suspend fun getWeatherData(query: String): WeatherRes {
        return fetchWeather(apiService.getWeatherData(query))
    }

    private suspend fun fetchWeather(call: Call<WeatherResponse>): WeatherRes {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.toWeatherDomain() ?: WeatherRes(city = "", timezone = 0, weather = emptyList())
                } else {
                    WeatherRes(city = "", timezone = 0, weather = emptyList())
                }
            } catch (e: Exception) {
                WeatherRes(city = "", timezone = 0, weather = emptyList())
            }
        }
    }
}