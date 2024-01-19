package com.example.skytracker.presentation

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skytracker.presentation.adapters.CitiesAdapter
import com.example.skytracker.presentation.adapters.WeatherAdapter
import com.example.skytracker.data.api.CityLight
import com.example.skytracker.databinding.ActivityCitySelectionBinding

class CitySelectionActivity : AppCompatActivity() {
    lateinit var binding: ActivityCitySelectionBinding
    private lateinit var citiesAdapter: CitiesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityList = listOf(
            CityLight("Москва"),
            CityLight("Санкт-Петербург"),
            CityLight("Новосибирск"),
            CityLight("Екатеринбург"),
            CityLight("Красноярск"),
            CityLight("Нижний Новгород"),
            CityLight("Казань"),
            CityLight("Челябинск"),
            CityLight("Омск"),
            CityLight("Самара"),
            CityLight("Ростов-на-Дону"),
            CityLight("Уфа"),
            CityLight("Пермь"),
            CityLight("Воронеж"),
            CityLight("Волгоград"),
            CityLight("Краснодар"),
            CityLight("Саратов"),
            CityLight("Тюмень"),
            CityLight("Тольятти"),
            CityLight("Ижевск"),
            CityLight("Барнаул"),
            CityLight("Ульяновск"),
            CityLight("Иркутск"),
            CityLight("Хабаровск"),
            CityLight("Ярославль"),
            CityLight("Владивосток"),
            CityLight("Махачкала"),
            CityLight("Томск"),
            CityLight("Оренбург"),
            CityLight("Кемерово"),
            CityLight("Новокузнецк"),
            CityLight("Рязань"),
            CityLight("Астрахань"),
            CityLight("Пенза"),
            CityLight("Липецк"),
            CityLight("Тула"),
            CityLight("Киров"),
            CityLight("Чебоксары"),
            CityLight("Калининград"),
            CityLight("Брянск"),
            CityLight("Курск"),
            CityLight("Иваново"),
            CityLight("Магнитогорск"),
            CityLight("Улан-Удэ"),
            CityLight("Тверь"),
            CityLight("Ставрополь"),
            CityLight("Нижний Тагил"),
            CityLight("Белгород"),
            CityLight("Архангельск")
        )

        val currentOrientation = resources.configuration.orientation

        citiesAdapter = CitiesAdapter(cityList, this)
        binding.citiesList.adapter = citiesAdapter
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
}