package com.example.domain_repository.di.modules

import com.example.domain_repository.di.modules.repository.WeatherRepositoryModule
import dagger.Module

@Module(
    includes = [
        WeatherRepositoryModule::class
    ]
)
class RepositoryModule {
}