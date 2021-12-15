package ru.brauer.cleanarchitecture.model.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.brauer.historyscreen.model.repository.AppDataBase

@Database(
    entities = [
        Word::class,
        ru.brauer.historyscreen.datasource.database.SearchWord::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase(), AppDataBase {
    abstract fun wordsDao(): WordDao
    abstract override fun searchWordDao(): ru.brauer.historyscreen.datasource.database.SearchWordDao
}