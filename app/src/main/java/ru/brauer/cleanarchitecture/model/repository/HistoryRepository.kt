package ru.brauer.cleanarchitecture.model.repository

import androidx.lifecycle.LiveData
import ru.brauer.cleanarchitecture.model.datasource.database.AppDataBase
import ru.brauer.cleanarchitecture.model.datasource.database.SearchWord

interface HistoryRepository<T> {
    fun getData(): LiveData<T>
}

class HistoryRepositoryImpl(private val database: AppDataBase) :
    HistoryRepository<List<SearchWord>> {
    override fun getData(): LiveData<List<SearchWord>> = database.searchWordDao().getAll()
}