package ru.brauer.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler

interface View {
    fun renderData(appState: AppState)
}

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}

interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}

interface Repository<T> {

    fun getData(word: String): Observable<T>
}

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}

sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()
}

class DataModel(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)

class Meanings(
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)

class Translation(@field:SerializedName("text") val translation: String?)

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}

//In the sake of testing
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}