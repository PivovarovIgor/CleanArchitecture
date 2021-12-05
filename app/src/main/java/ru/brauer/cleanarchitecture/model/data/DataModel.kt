package ru.brauer.cleanarchitecture.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DataModel(
    @SerializedName(JSON_FIELD_ID) val id: Int,
    @SerializedName(JSON_FIELD_TEXT) val text: String?,
    @SerializedName(JSON_FIELD_MEANINGS) val meanings: List<Meanings>?
) : Parcelable {
    companion object {
        private const val JSON_FIELD_ID = "id"
        private const val JSON_FIELD_TEXT = "text"
        private const val JSON_FIELD_MEANINGS = "meanings"
    }
}