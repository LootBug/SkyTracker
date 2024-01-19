package com.example.skytracker.domain.interacters

import com.example.skytracker.domain.CityRepository
import javax.inject.Inject

class GetLastCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend fun execute(): String? {
        return cityRepository.getLastCity()
    }
}