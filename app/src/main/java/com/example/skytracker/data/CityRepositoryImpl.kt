package com.example.skytracker.data

import com.example.skytracker.data.database.LastCityDao
import com.example.skytracker.data.database.LastCityEntity
import com.example.skytracker.domain.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dao: LastCityDao
): CityRepository {
    override suspend fun getLastCity(): String {
        return if (dao.getLastCity().isNotEmpty()) {
            dao.getLastCity()[0].lastCity
        } else {
            ""
        }
    }

    override suspend fun insertLastCity(city: String) {
        dao.insertLastCity(LastCityEntity(lastCity = city))
    }

}