package ru.brauer.cleanarchitecture.model.datasource.database

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.brauer.appcore.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSource

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    private val scopeIo = CoroutineScope(Dispatchers.IO)
    private var databaseResultSearchWriting: Job? = null
    private var databaseSearchWordWriting: Job? = null

    private val database: AppDataBase by inject(AppDataBase::class.java)

    override fun getData(word: String): Observable<List<DataModel>> =
        Observable.fromCallable { database.wordsDao().findWord(word).toBusinessModel() }

    fun writeResultsOfSearch(words: List<DataModel>) {
        databaseResultSearchWriting?.cancel()
        databaseResultSearchWriting = scopeIo.launch {
            database.wordsDao().insert(words.toDatabaseEntity())
        }
    }

    private fun List<Word>.toBusinessModel(): List<DataModel> =
        map {
            DataModel(
                id = it.id,
                text = it.name,
                listOf()
            )
        }

    private fun List<DataModel>.toDatabaseEntity(): List<Word> =
        map {
            Word(
                id = it.id,
                name = it.text ?: ""
            )
        }

    fun writeHistoryOfSearching(searchWord: String) {
        databaseSearchWordWriting?.cancel()
        databaseSearchWordWriting = scopeIo.launch {
            database.searchWordDao().insert(
                ru.brauer.historyscreen.datasource.database.SearchWord(
                    System.currentTimeMillis(),
                    searchWord
                )
            )
        }
    }
}