package ru.brauer.historyscreen.datasource.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchWordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchWord: SearchWord)

    @Query("SELECT * FROM search_history")
    fun getAll(): LiveData<List<SearchWord>>

    @Query("SELECT * FROM search_history ORDER BY data_time DESC LIMIT 1")
    fun getLastSearchedWord(): SearchWord?
}