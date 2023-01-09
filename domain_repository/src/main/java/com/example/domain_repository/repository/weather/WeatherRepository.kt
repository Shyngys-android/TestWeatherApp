package com.example.domain_repository.repository.weather

import androidx.lifecycle.LiveData
import com.example.data_models.models.city.Cities
import com.example.data_models.models.network.Resource
import com.example.data_models.models.weather.WeatherInfo

interface WeatherRepository {

    fun getCitiesInfo(): LiveData<Resource<Cities>>

    fun getWeatherInfo(params: Map<String, String>): LiveData<Resource<WeatherInfo>>
}