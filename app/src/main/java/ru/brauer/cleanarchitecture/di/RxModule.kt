package ru.brauer.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.brauer.cleanarchitecture.rx.ISchedulerProvider
import ru.brauer.cleanarchitecture.rx.SchedulerProvider
import javax.inject.Singleton

@Module
class RxModule {

    @Singleton
    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun schedulerProvider(): ISchedulerProvider = SchedulerProvider()
}