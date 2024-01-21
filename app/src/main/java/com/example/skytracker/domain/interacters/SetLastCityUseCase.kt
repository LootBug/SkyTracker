package com.example.skytracker.domain.interacters

import com.example.skytracker.domain.CityRepository
import javax.inject.Inject

class SetLastCityUseCase @Inject constructor(
    private val repository: CityRepository
) {
    suspend fun execute(city: String) {
        repository.insertLastCity(city)
    }
}