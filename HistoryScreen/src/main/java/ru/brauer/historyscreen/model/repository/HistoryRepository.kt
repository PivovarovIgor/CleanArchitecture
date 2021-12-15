package ru.brauer.historyscreen.model.repository

import androidx.lifecycle.LiveData

interface HistoryRepository<T> {
    fun getData(): LiveData<T>
}

