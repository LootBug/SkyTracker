package com.example.skytracker.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skytracker.CitySelectionActivity
import com.example.skytracker.MainActivity
import com.example.skytracker.data.api.CityLight
import com.example.skytracker.data.api.Instance
import com.example.skytracker.data.api.WeatherData
import com.example.skytracker.data.api.WeatherResponse
import com.example.skytracker.databinding.ActivityMainBinding
import com.example.skytracker.databinding.CityItemBinding
import com.example.skytracker.databinding.WeatherItemBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


class CitiesAdapter(
    private val city: List<CityLight>,
    private val context: Context
) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    private lateinit var weatherAdapter: WeatherAdapter

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
                        weatherAdapter = weather?.let { WeatherAdapter(it, context, response.city.timezone) }!!
                        MainActivity.binding.weatherList.adapter = weatherAdapter
                        MainActivity.binding.weatherList.layoutManager = LinearLayoutManager(context)
                        MainActivity.binding.city.text = response.city.name
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                }
            })


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
        fun bind(city: CityLight) {
            binding.city.text = city.name
        }
    }
}