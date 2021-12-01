package ru.brauer.cleanarchitecture.di

import dagger.Component
import ru.brauer.cleanarchitecture.di.viewmodel.ViewModelModule
import ru.brauer.cleanarchitecture.view.main.MainActivity
import ru.brauer.cleanarchitecture.view.meanings.MeaningsActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        InteractorModule::class,
        DataModule::class,
        RxModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(meaningsActivity: MeaningsActivity)
}