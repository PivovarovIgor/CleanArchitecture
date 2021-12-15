package ru.brauer.appcore.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Meanings(
    @SerializedName(JSON_FIELD_TRANSLATION) val translation: Translation?,
    @SerializedName(JSON_FIELD_IMAGE_URL) val imageUrl: String?
) : Parcelable {
    companion object {
        private const val JSON_FIELD_TRANSLATION = "translation"
        private const val JSON_FIELD_IMAGE_URL = "imageUrl"
    }
}