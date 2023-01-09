package com.example.testweatherapp.ui.city_info

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.data_models.enums.CityEnums
import com.example.data_models.models.weather.DailyInfo
import com.example.data_models.models.weather.WeatherInfo
import com.example.domain_repository.repository.weather.WeatherRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.mutableMapOf
import kotlin.collections.orEmpty
import kotlin.collections.set

class CityInfoViewModel
@Inject constructor(
    private val repository: WeatherRepository,
    app: Application
) : AndroidViewModel(app) {

    private val apiKey = "1abef5bded8140aaaa484e9bbcf0fc46"
    private var cityName = ""
    val titleField = MutableLiveData<String>()
    private val refresh = MutableLiveData<Map<String, String>>()

    fun setArgs(cityName: String) {
        this.cityName = cityName
        titleField.value = "Погода на неделю $cityName"
        val params = mutableMapOf<String, String>()
        CityEnums.values().forEach {
            if (it.cityName == cityName) {
                params["city_id"] = it.id.toString()
            }
        }
        params["start_date"] = convertDateToString(Date(), "YYYY-MM-DD")
        params["end_date"] = getSpecificDate(Date(), 7, "YYYY-MM-DD")
        params["key"] = apiKey
        refresh.value = params
    }

    val getWeatherInfoResource = Transformations.switchMap(refresh) {
        repository.getWeatherInfo(it)
    }

    private val _listOfDayInfo = MutableLiveData<List<DailyInfo>>()
    val listOfDayInfo: LiveData<List<DailyInfo>>
        get() = _listOfDayInfo

    fun onGetWeatherInfoSuccess(weatherInfo: WeatherInfo?) {
        _listOfDayInfo.value = weatherInfo?.data.orEmpty()
    }

    fun onBtnWeekClick() {
        titleField.value = "Погода на неделю $cityName"
        val params = mutableMapOf<String, String>()
        CityEnums.values().forEach {
            if (it.cityName == cityName) {
                params["city_id"] = it.id.toString()
            }
        }
        params["start_date"] = convertDateToString(Date(), "YYYY-MM-DD")
        params["end_date"] = getSpecificDate(Date(), 7, "YYYY-MM-DD")
        params["key"] = apiKey
        refresh.value = params
    }

    fun onBtnMonthClick() {
        titleField.value = "Погода на месяц $cityName"
        val params = mutableMapOf<String, String>()
        CityEnums.values().forEach {
            if (it.cityName == cityName) {
                params["city_id"] = it.id.toString()
            }
        }
        params["start_date"] = convertDateToString(Date(), "YYYY-MM-DD")
        params["end_date"] = getSpecificDate(Date(), 30, "YYYY-MM-DD")
        params["key"] = apiKey
        refresh.value = params
    }

    private fun convertDateToString(date: Date, pattern: String): String {
        val sdf = SimpleDateFormat(pattern)
        return sdf.format(date)
    }

    private fun getSpecificDate(date: Date, num: Int, pattern: String): String {
        var dt = Date()
        val c = Calendar.getInstance()
        c.time = dt
        c.add(Calendar.DATE, num)
        dt = c.time
        return convertDateToString(dt, pattern)
    }
}