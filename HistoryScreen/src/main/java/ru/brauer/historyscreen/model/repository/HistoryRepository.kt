package ru.brauer.historyscreen.model.repository

import androidx.lifecycle.LiveData
import ru.brauer.historyscreen.datasource.database.SearchWord

interface HistoryRepository<T> {
    fun getData(): LiveData<T>
    fun getLastSearchedWord(): SearchWord?
}

