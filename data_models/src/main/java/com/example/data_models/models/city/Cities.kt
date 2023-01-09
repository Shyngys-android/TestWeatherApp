package com.example.data_models.models.city

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cities(
    val data: List<City>? = null
) : Parcelable

@Parcelize
data class City(
    val city_name: String? = null,
    val temp: String? = null
) : Parcelable {
    fun getTempInC() = "$temp °C"
}
