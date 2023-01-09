package com.example.testweatherapp.di.component

import android.app.Application
import com.example.domain_repository.di.component.RepositoryComponent
import com.example.testweatherapp.App
import com.example.testweatherapp.di.contributes_android_injector.MainActivityModule
import com.example.testweatherapp.di.modules.AppModule
import com.example.testweatherapp.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        AppModule::class
    ],
    dependencies = [
        RepositoryComponent::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun repositoryComponent(repositoryComponent: RepositoryComponent): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}