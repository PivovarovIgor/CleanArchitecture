package ru.brauer.historyscreen.datasource.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchWordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchWord: ru.brauer.historyscreen.datasource.database.SearchWord)

    @Query("SELECT * FROM search_history")
    fun getAll(): LiveData<List<ru.brauer.historyscreen.datasource.database.SearchWord>>
}