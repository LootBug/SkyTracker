package com.example.skytracker.presentation.city_selection

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skytracker.presentation.adapters.CitiesAdapter
import com.example.skytracker.databinding.ActivityCitySelectionBinding
import com.example.skytracker.domain.models.City
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CitySelectionActivity : AppCompatActivity(), CitiesContract.View {
    lateinit var binding: ActivityCitySelectionBinding
    private lateinit var citiesAdapter: CitiesAdapter
    @Inject
    lateinit var presenter: CitiesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentOrientation = resources.configuration.orientation

        presenter.onViewCreated()

        handleOrientationChange(currentOrientation)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleOrientationChange(newConfig.orientation)
    }

    private fun handleOrientationChange(orientation: Int) {
        // Ваш код для обработки изменений ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.citiesList.layoutManager = LinearLayoutManager(this@CitySelectionActivity)
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.citiesList.layoutManager = GridLayoutManager(this@CitySelectionActivity, 2)
        }
    }

    override fun showCities(cityList: List<City>) {
        citiesAdapter = CitiesAdapter(cityList, this)
        binding.citiesList.adapter = citiesAdapter
    }
}