package ru.brauer.cleanarchitecture.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.brauer.cleanarchitecture.view.main.MainViewModel
import ru.brauer.cleanarchitecture.view.meanings.MeaningsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MeaningsViewModel::class)
    abstract fun meaningViewModel(viewModel: MeaningsViewModel): ViewModel
}