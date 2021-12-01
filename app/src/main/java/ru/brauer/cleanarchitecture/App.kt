package ru.brauer.cleanarchitecture

import android.app.Application
import ru.brauer.cleanarchitecture.di.AppComponent
import ru.brauer.cleanarchitecture.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}