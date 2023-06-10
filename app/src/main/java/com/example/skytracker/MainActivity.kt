package com.example.skytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skytracker.adapters.WeatherAdapter
import com.example.skytracker.data.api.Instance
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val service = Instance.api
        val call = service.getWeatherData()
        fetchWeather(call)
    }

    private fun fetchWeather(call: Call<WeatherResponse>) {
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val response = response.body()
                    val weather = response?.list
                    weatherAdapter = weather?.let { WeatherAdapter(it, this@MainActivity, response.city.timezone) }!!
                    binding.weatherList.adapter = weatherAdapter
                    binding.weatherList.layoutManager = LinearLayoutManager(this@MainActivity)
                } else {
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}