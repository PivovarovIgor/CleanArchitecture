package ru.brauer.cleanarchitecture.model.datasource.database

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
}