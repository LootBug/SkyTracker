package com.example.skytracker.di

import android.app.Activity
import com.example.skytracker.presentation.city_selection.CitiesContract
import com.example.skytracker.presentation.city_selection.CitiesPresenter
import com.example.skytracker.presentation.city_selection.CitySelectionActivity
import com.example.skytracker.presentation.main.MainActivity
import com.example.skytracker.presentation.main.MainContract
import com.example.skytracker.presentation.main.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class CitiesModule {

    @Binds
    abstract fun bindActivity(activity: CitySelectionActivity): CitiesContract.View

    @Binds
    abstract fun bindPresenter(impl: CitiesPresenter): CitiesContract.Presenter

}

@InstallIn(ActivityComponent::class)
@Module
object CitySelectionActivityModule {
    @Provides
    fun provideActivity(activity: Activity): CitySelectionActivity {
        return activity as CitySelectionActivity
    }
}