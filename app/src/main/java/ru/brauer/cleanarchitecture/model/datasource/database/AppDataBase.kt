package ru.brauer.cleanarchitecture.model.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Word::class,
        SearchWord::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordsDao(): WordDao
    abstract fun searchWordDao(): SearchWordDao
}