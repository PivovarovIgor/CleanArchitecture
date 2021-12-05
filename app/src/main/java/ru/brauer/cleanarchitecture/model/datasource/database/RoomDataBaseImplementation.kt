package ru.brauer.cleanarchitecture.model.datasource.database

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSource

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    private val scopeIo = CoroutineScope(Dispatchers.IO)
    private var databaseWordWriting: Job? = null

    private val database: AppDataBase by inject(AppDataBase::class.java)

    override fun getData(word: String): Observable<List<DataModel>> =
        Observable.fromCallable { database.wordsDao().findWord(word).toBusinessModel() }

    fun writeData(words: List<DataModel>) {
        databaseWordWriting?.cancel()
        databaseWordWriting = scopeIo.launch {
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
}