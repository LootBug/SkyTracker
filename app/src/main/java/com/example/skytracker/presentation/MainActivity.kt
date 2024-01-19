package com.example.skytracker.presentation

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skytracker.R
import com.example.skytracker.presentation.adapters.WeatherAdapter
import com.example.skytracker.data.api.Instance
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.data.database.LastCityDao
import com.example.skytracker.data.database.LastCityDatabase
import com.example.skytracker.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var binding: ActivityMainBinding
    }
    private lateinit var lastCityDao: LastCityDao
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch {
            lastCityDao = LastCityDatabase
                .getDatabase(this@MainActivity)
                .lastCityDao()

            val service = Instance.api
            val lastCity = lastCityDao.getLastCity()
            if (lastCity.isEmpty()) {
                runOnUiThread {
                    fetchWeather(service.getWeatherDataInit())
                }
            } else {
                runOnUiThread {
                    fetchWeather(service.getWeatherData(lastCity[0].lastCity))
                }
            }
        }

        val currentOrientation = resources.configuration.orientation
        handleOrientationChange(currentOrientation)

    }

    private fun fetchWeather(call: Call<WeatherResponse>) {
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val response = response.body()
                    val weather = response?.list
                    weatherAdapter = weather?.let { WeatherAdapter(it, this@MainActivity, response.city.timezone) }!!
                    binding.weatherList.adapter = weatherAdapter

                    binding.city.text = response.city.name
                } else {
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

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
}