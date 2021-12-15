package ru.brauer.cleanarchitecture.model.data

import ru.brauer.appcore.model.data.DataModel

sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()
}