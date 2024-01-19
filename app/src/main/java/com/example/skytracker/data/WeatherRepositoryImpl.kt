package com.example.skytracker.data

import android.content.Context
import android.widget.Toast
import com.example.skytracker.data.api.ApiService
import com.example.skytracker.data.api.WeatherData
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.domain.WeatherRepository
import com.example.skytracker.domain.mappres.toWeatherDomain
import com.example.skytracker.domain.models.Weather
import com.example.skytracker.presentation.MainActivity
import com.example.skytracker.presentation.adapters.WeatherAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext private val applicationContext: Context
) : WeatherRepository {
    override suspend fun getWeatherDataInit(): List<Weather> {
        return fetchWeather(apiService.getWeatherDataInit())
    }

    override suspend fun getWeatherData(query: String): List<Weather> {
        return fetchWeather(apiService.getWeatherData(query))
    }

    private fun fetchWeather(call: Call<WeatherResponse>): List<Weather> {
        var res: List<WeatherData>? = null
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val response = response.body()
                    res = response?.list!!

                } else {
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })

        return res?.map { it.toWeatherDomain() } ?: emptyList()
    }
}