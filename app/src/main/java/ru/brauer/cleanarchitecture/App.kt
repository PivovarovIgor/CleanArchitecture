package ru.brauer.cleanarchitecture

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.brauer.appcore.di.DIOfCore
import ru.brauer.cleanarchitecture.di.DI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(DI.mainModule, DIOfCore.coreModule))
        }
    }
}