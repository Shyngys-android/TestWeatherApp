package com.example.domain_repository.repository.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data_models.models.city.Cities
import com.example.data_models.models.network.Resource
import com.example.data_models.models.weather.WeatherInfo
import com.example.data_network.api.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    private val apiKey = "1abef5bded8140aaaa484e9bbcf0fc46"

    override fun getCitiesInfo(): LiveData<Resource<Cities>> {
        val liveData = MutableLiveData<Resource<Cities>>()

        val params = mutableMapOf<String, String>()

        params["cities"] = "1526384, 1526273, 1526601, 1537939"
        params["key"] = apiKey

        api
            .getCities(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.postValue(Resource.loading(null)) }
            .subscribeWith(object : DisposableSingleObserver<Cities>() {

                override fun onSuccess(t: Cities) {
                    liveData.postValue(Resource.success(t))
                }

                override fun onError(e: Throwable) {

                }

            })

        return liveData
    }

    override fun getWeatherInfo(params: Map<String, String>): LiveData<Resource<WeatherInfo>> {
        val liveData = MutableLiveData<Resource<WeatherInfo>>()

        api
            .getWeather(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.postValue(Resource.loading(null)) }
            .subscribeWith(object : DisposableSingleObserver<WeatherInfo>() {

                override fun onSuccess(t: WeatherInfo) {
                    liveData.postValue(Resource.success(t))
                }

                override fun onError(e: Throwable) {

                }

            })

        return liveData
    }
}