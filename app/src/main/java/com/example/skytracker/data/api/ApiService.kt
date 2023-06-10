package com.example.skytracker.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast?appid=8e9f3206bb232161e3e358b974dacf4f&q=Красноярск&units=metric&lang=ru")
    fun getWeatherData(): Call<WeatherResponse>
}