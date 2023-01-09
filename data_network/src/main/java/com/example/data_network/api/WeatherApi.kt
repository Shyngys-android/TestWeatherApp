package com.example.data_network.api

import com.example.data_models.models.city.Cities
import com.example.data_models.models.weather.WeatherInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherApi {

    @GET("current")
    fun getCities(@QueryMap params: Map<String, String>): Single<Cities>

    @GET("history/daily")
    fun getWeather(@QueryMap params: Map<String, String>): Single<WeatherInfo>
}