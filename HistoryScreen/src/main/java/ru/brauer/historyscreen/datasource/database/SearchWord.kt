package ru.brauer.historyscreen.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchWord(
    @PrimaryKey @ColumnInfo(name = "data_time") val dateTime: Long,
    @ColumnInfo(name = "search_word") val searchWord: String
)