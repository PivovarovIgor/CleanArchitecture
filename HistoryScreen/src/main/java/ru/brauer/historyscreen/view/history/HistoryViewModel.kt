package ru.brauer.historyscreen.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.brauer.historyscreen.datasource.database.SearchWord
import ru.brauer.historyscreen.model.repository.HistoryRepository

class HistoryViewModel(private val repository: HistoryRepository<List<SearchWord>>) : ViewModel() {
    fun getData(): LiveData<List<SearchWord>> = repository.getData()
}
