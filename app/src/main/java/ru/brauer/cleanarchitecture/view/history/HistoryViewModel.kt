package ru.brauer.cleanarchitecture.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.brauer.cleanarchitecture.model.datasource.database.SearchWord
import ru.brauer.cleanarchitecture.model.repository.HistoryRepository

class HistoryViewModel(private val repository: HistoryRepository<List<SearchWord>>) : ViewModel() {
    fun getData(): LiveData<List<SearchWord>> = repository.getData()
}
