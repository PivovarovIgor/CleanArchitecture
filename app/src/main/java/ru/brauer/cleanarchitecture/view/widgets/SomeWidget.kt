package ru.brauer.cleanarchitecture.view.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Bitmap
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import ru.brauer.cleanarchitecture.R
import ru.brauer.cleanarchitecture.interactor.Interactor
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.utils.toUrl
import ru.brauer.historyscreen.datasource.database.SearchWord
import ru.brauer.historyscreen.model.repository.HistoryRepository

class SomeWidget : AppWidgetProvider(), KoinComponent {

    private val interactor: Interactor<AppState> by getKoin().inject()
    private val historyRepository: HistoryRepository<List<SearchWord>> by getKoin().inject()

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        if (context == null || appWidgetIds == null) return

        val disposable = Single.fromCallable { requireNotNull(historyRepository.getLastSearchedWord()) }
            .subscribeOn(Schedulers.io())
            .flatMapObservable { word ->
                interactor.getData(word.searchWord, true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it is AppState.Success) {
                    it.data
                        ?.firstOrNull()
                        ?.meanings
                        ?.firstOrNull()
                        ?.imageUrl
                        ?.let { url ->
                            showImageOnWidget(url, context, appWidgetIds)
                        }
                }
            }
    }

    private fun showImageOnWidget(urlImage: String, context: Context, appWidgetIds: IntArray) {
        val remoteViews = RemoteViews(context.packageName, R.layout.some_widget)
        val appWidgetTarget = object : AppWidgetTarget(
            context.applicationContext,
            R.id.img_on_widget,
            remoteViews,
            *appWidgetIds
        ) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                super.onResourceReady(resource, transition)
            }
        }
        val requestOptions = RequestOptions()
            .override(IMAGE_SIZE, IMAGE_SIZE)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)

        Glide.with(context.applicationContext)
            .asBitmap()
            .load(urlImage.toUrl())
            .apply(requestOptions)
            .into(appWidgetTarget)
    }

    companion object {
        private const val IMAGE_SIZE = 300
    }
}