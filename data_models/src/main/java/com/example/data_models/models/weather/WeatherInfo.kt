package com.example.data_models.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInfo(
    val city_id: String? = null,
    val city_name: String? = null,
    val data: List<DailyInfo>? = null
) : Parcelable

@Parcelize
data class DailyInfo(
    val datetime: String? = null,
    val temp: String? = null
) : Parcelable
