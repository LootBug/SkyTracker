package com.example.skytracker.di

import android.app.Activity
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
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): MainContract.View

    @Binds
    abstract fun bindPresenter(impl: MainPresenter): MainContract.Presenter

}

@InstallIn(ActivityComponent::class)
@Module
object MainActivityModule {
    @Provides
    fun provideActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }
}