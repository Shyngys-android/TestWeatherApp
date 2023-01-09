package com.example.domain_repository.di.modules

import com.example.domain_repository.di.modules.api.WeatherApiModule
import dagger.Module

@Module(
    includes = [
        WeatherApiModule::class
    ]
)
class ApiModule {
}