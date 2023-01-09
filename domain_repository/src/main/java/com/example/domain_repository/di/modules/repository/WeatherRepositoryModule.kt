package com.example.domain_repository.di.modules.repository

import com.example.domain_repository.repository.weather.WeatherRepository
import com.example.domain_repository.repository.weather.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class WeatherRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}