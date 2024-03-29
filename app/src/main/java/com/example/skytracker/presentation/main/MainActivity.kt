package com.example.skytracker.presentation.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skytracker.R
import com.example.skytracker.presentation.adapters.WeatherAdapter
import com.example.skytracker.databinding.ActivityMainBinding
import com.example.skytracker.domain.models.WeatherRes
import com.example.skytracker.presentation.city_selection.CitySelectionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    companion object {
        lateinit var binding: ActivityMainBinding
    }
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        GlobalScope.launch {
            val lastCity = presenter.getLastCity()
            if (lastCity==null) {
                presenter.onViewCreated()
            } else {
                presenter.onCitySelected(lastCity)
            }

        }

        val currentOrientation = resources.configuration.orientation
        handleOrientationChange(currentOrientation)

    }

//    private fun fetchWeather(call: Call<WeatherResponse>) {
//        call.enqueue(object : Callback<WeatherResponse> {
//            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
//                if (response.isSuccessful) {
//                    val response = response.body()
//                    val weather = response?.list
//                    weatherAdapter = weather?.let { WeatherAdapter(it, this@MainActivity, response.city.timezone) }!!
//                    binding.weatherList.adapter = weatherAdapter
//
//                    binding.city.text = response.city.name
//                } else {
//                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
//            }
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.city_selection -> {
                val intent = Intent(this, CitySelectionActivity::class.java)
                this.startActivity(intent)
            }
        }
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleOrientationChange(newConfig.orientation)
    }

    private fun handleOrientationChange(orientation: Int) {
        // Ваш код для обработки изменений ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.weatherList.layoutManager = LinearLayoutManager(this@MainActivity)
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.weatherList.layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    override fun showWeather(weather: WeatherRes) {
        weatherAdapter = WeatherAdapter(weather.weather, this@MainActivity, weather.timezone)
        binding.weatherList.adapter = weatherAdapter
        binding.city.text = weather.city
    }
}