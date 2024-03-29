package com.example.skytracker.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.skytracker.data.api.WeatherData
import com.example.skytracker.databinding.WeatherItemBinding
import com.example.skytracker.domain.models.Weather
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class WeatherAdapter(
    private val weatherData: List<Weather>,
    private val context: Context,
    private val offset: Int
) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherData[position]
        holder.bind(weather, position == 0)

        holder.itemView.setOnClickListener {

        }
    }


    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class ViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(weatherData: Weather, isFirst: Boolean) {
            binding.degree.text = weatherData.temp.roundToInt().toString() + "°C"
            binding.pressure.text = (weatherData.pressure * 0.75).roundToInt().toString() + " мм рт. ст."
            binding.humidity.text = weatherData.humidity.toString() + " %"
            binding.wind.text = weatherData.windSpeed.roundToInt().toString() + " м/c"
            Picasso.get()
                .load("https://openweathermap.org/img/wn/" + weatherData.weatherIcon + "@2x.png")
                .into(binding.weatherIcon)
            if (isFirst) {
                binding.date.text = "Сейчас"
            } else {
                binding.date.text =
                    LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(weatherData.date * 1000 + offset * 1000 - 10800 * 1000),
                        ZoneOffset.UTC
                    )
                        .format(DateTimeFormatter.ofPattern("dd MMMM, HH:mm", Locale("ru", "RU")))
            }
        }
    }
}