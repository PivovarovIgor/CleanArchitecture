package ru.brauer.appcore.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.brauer.appcore.view.OnLineLiveData

object DIOfCore {
    val coreModule = module {
        single { OnLineLiveData(androidContext()) }
    }
}