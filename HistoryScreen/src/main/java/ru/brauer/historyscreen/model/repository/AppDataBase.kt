package ru.brauer.historyscreen.model.repository

import ru.brauer.historyscreen.datasource.database.SearchWordDao

interface AppDataBase {
    abstract fun searchWordDao(): SearchWordDao
}