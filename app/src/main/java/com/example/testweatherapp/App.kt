package com.example.testweatherapp

import com.example.domain_repository.di.component.DaggerRepositoryComponent
import com.example.testweatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    private val repositoryComponent = DaggerRepositoryComponent
        .builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this)
            .repositoryComponent(repositoryComponent).build()
    }
}