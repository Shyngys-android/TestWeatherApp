package com.example.testweatherapp.di.contributes_android_injector

import com.example.testweatherapp.ui.cities.CitiesFragment
import com.example.testweatherapp.ui.city_info.CityInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun citiesFragment(): CitiesFragment

    @ContributesAndroidInjector
    internal abstract fun cityInfoFragment(): CityInfoFragment
}