package com.example.skytracker.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.skytracker.presentation.city_selection.CitySelectionActivity
import com.example.skytracker.presentation.main.MainActivity
import com.example.skytracker.data.api.Instance
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.data.database.LastCityDao
import com.example.skytracker.data.database.LastCityDatabase
import com.example.skytracker.data.database.LastCityEntity
import com.example.skytracker.databinding.CityItemBinding
import com.example.skytracker.domain.mappres.toWeatherModel
import com.example.skytracker.domain.models.City
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class CitiesAdapter(
    private val city: List<City>,
    private val context: Context
) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var lastCityDao: LastCityDao

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = city[position]
        holder.bind(city)

        holder.itemView.setOnClickListener {
            MainActivity.binding.city.text = city.name

            val service = Instance.api
            val call = service.getWeatherData(city.name)

            call.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val response = response.body()
                        val weather = response?.list
                        weatherAdapter = weather?.let { WeatherAdapter(it.map { it.toWeatherModel() }, context, response.city.timezone) }!!
                        MainActivity.binding.weatherList.adapter = weatherAdapter

                        MainActivity.binding.city.text = response.city.name
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                }
            })

            GlobalScope.launch {
                lastCityDao = LastCityDatabase
                    .getDatabase(context)
                    .lastCityDao()

                lastCityDao.insertLastCity(LastCityEntity(0, city.name))
            }

            if (context is CitySelectionActivity) {
                context.finish()
            }
        }
    }


    override fun getItemCount(): Int {
        return city.size
    }

    inner class ViewHolder(private val binding: CityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(city: City) {
            binding.city.text = city.name
        }
    }
}