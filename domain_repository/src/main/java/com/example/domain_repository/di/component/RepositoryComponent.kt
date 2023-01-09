package com.example.domain_repository.di.component

import android.app.Application
import com.example.domain_repository.di.modules.ApiModule
import com.example.domain_repository.di.modules.NetworkModule
import com.example.domain_repository.di.modules.RepositoryModule
import com.example.domain_repository.repository.weather.WeatherRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
interface RepositoryComponent {

    fun getWeatherRepository() : WeatherRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): RepositoryComponent
    }

}