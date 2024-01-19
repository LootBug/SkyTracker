package com.example.skytracker.domain

interface CityRepository {

    suspend fun getLastCity() : String?

    suspend fun insertLastCity(city: String)
}