package com.example.testweatherapp.ui.cities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.data_models.models.city.Cities
import com.example.data_models.models.city.City
import com.example.domain_repository.repository.weather.WeatherRepository
import com.example.testweatherapp.ui.common.callbacks.RecyclerViewItemClickCallback
import javax.inject.Inject

class CitiesViewModel
@Inject constructor(
    private val repository: WeatherRepository,
    app: Application
) : AndroidViewModel(app) {

    private val refresh = MutableLiveData<Unit>()

    init {
        refresh.value = Unit
    }

    val getCitiesResource = Transformations.switchMap(refresh) {
        repository.getCitiesInfo()
    }

    private val _listOfCity = MutableLiveData<List<City>>()
    val listOfCity: LiveData<List<City>>
        get() = _listOfCity

    fun onGetCitiesInfoSuccess(cities: Cities?) {
        _listOfCity.value = cities?.data.orEmpty()
    }

    private val _navigateToInfo = MutableLiveData<String>()
    val navigateInfo: LiveData<String>
        get() = _navigateToInfo

    val recyclerViewItemClickCallback = object :
        RecyclerViewItemClickCallback {
        override fun onRecyclerViewItemClick(any: Any) {
            when(any) {
                is City -> {
                    _navigateToInfo.value = any.city_name?:""
                }
            }
        }
    }
}