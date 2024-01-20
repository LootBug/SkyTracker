package com.example.skytracker.presentation.city_selection

import com.example.skytracker.domain.models.City
import javax.inject.Inject

class CitiesPresenter @Inject constructor(
    private val view: CitiesContract.View
): CitiesContract.Presenter {
    override fun onViewCreated() {
        val cityList = listOf(
            City("Москва"),
            City("Санкт-Петербург"),
            City("Новосибирск"),
            City("Екатеринбург"),
            City("Красноярск"),
            City("Нижний Новгород"),
            City("Казань"),
            City("Челябинск"),
            City("Омск"),
            City("Самара"),
            City("Ростов-на-Дону"),
            City("Уфа"),
            City("Пермь"),
            City("Воронеж"),
            City("Волгоград"),
            City("Краснодар"),
            City("Саратов"),
            City("Тюмень"),
            City("Тольятти"),
            City("Ижевск"),
            City("Барнаул"),
            City("Ульяновск"),
            City("Иркутск"),
            City("Хабаровск"),
            City("Ярославль"),
            City("Владивосток"),
            City("Махачкала"),
            City("Томск"),
            City("Оренбург"),
            City("Кемерово"),
            City("Новокузнецк"),
            City("Рязань"),
            City("Астрахань"),
            City("Пенза"),
            City("Липецк"),
            City("Тула"),
            City("Киров"),
            City("Чебоксары"),
            City("Калининград"),
            City("Брянск"),
            City("Курск"),
            City("Иваново"),
            City("Магнитогорск"),
            City("Улан-Удэ"),
            City("Тверь"),
            City("Ставрополь"),
            City("Нижний Тагил"),
            City("Белгород"),
            City("Архангельск")
        )

        view.showCities(cityList)

    }
}