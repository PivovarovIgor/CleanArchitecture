package ru.brauer.cleanarchitecture.model.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Word::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordsDao(): WordDao
}