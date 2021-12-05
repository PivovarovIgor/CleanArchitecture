package ru.brauer.cleanarchitecture

import android.app.Application
import org.koin.core.context.startKoin
import ru.brauer.cleanarchitecture.di.DI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(DI.mainModule))
        }
    }
}