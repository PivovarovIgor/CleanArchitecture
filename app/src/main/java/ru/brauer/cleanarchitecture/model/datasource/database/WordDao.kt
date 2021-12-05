package ru.brauer.cleanarchitecture.model.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM words WHERE name = :word")
    fun findWord(word: String): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(words: List<Word>)
}