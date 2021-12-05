package ru.brauer.cleanarchitecture.model.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.brauer.cleanarchitecture.model.datasource.database.Word.Companion.TABLE_NAME_WORDS

@Entity(tableName = TABLE_NAME_WORDS)
data class Word(
    @PrimaryKey val id: Int,
    val name: String
) {
    companion object {
        const val TABLE_NAME_WORDS = "words"
    }
}