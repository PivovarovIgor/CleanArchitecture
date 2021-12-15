package ru.brauer.historyscreen.model.repository

import androidx.lifecycle.LiveData
import ru.brauer.historyscreen.datasource.database.SearchWord

class HistoryRepositoryImpl(private val database: AppDataBase) :
    HistoryRepository<List<SearchWord>> {
    override fun getData(): LiveData<List<SearchWord>> = database.searchWordDao().getAll()
}