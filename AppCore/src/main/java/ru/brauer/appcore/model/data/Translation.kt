package ru.brauer.appcore.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Translation(
    @SerializedName(JSON_FIELD_TEXT) val translation: String?,
    @SerializedName(JSON_FIELD_NOTE) val note: String?
) : Parcelable {
    companion object {
        private const val JSON_FIELD_TEXT = "text"
        private const val JSON_FIELD_NOTE = "note"
    }
}