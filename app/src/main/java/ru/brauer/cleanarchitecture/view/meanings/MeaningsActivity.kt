package ru.brauer.cleanarchitecture.view.meanings

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.brauer.cleanarchitecture.databinding.ActivityMeaningsBinding
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.data.Meanings
import ru.brauer.cleanarchitecture.presenter.Presenter
import ru.brauer.cleanarchitecture.view.base.BaseActivity
import ru.brauer.cleanarchitecture.view.base.View
import ru.brauer.cleanarchitecture.view.meanings.adapter.MeaningsAdapter

class MeaningsActivity : BaseActivity<List<Meanings>>() {

    private val binding: ActivityMeaningsBinding by lazy {
        ActivityMeaningsBinding.inflate(layoutInflater)
    }

    private val adapter: MeaningsAdapter by lazy {
        MeaningsAdapter()
    }

    private val meaningsPresenter: MeaningsPresenterImpl<List<Meanings>, View<List<Meanings>>> by lazy {
        MeaningsPresenterImpl()
    }

    private val dataModel: DataModel by lazy {
        requireNotNull(intent.getParcelableExtra(MEANINGS_ACTIVITY_TAG))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.recyclerviewMeanings) {
            layoutManager = LinearLayoutManager(this@MeaningsActivity)
            adapter = this@MeaningsActivity.adapter
        }
    }

    override fun createPresenter(): Presenter<List<Meanings>, View<List<Meanings>>> {
        return meaningsPresenter
    }

    override fun onStart() {
        super.onStart()
        meaningsPresenter.getData(dataModel)
    }

    override fun renderData(appState: List<Meanings>) {
        adapter.setData(appState)
    }

    companion object {
        const val MEANINGS_ACTIVITY_TAG = "MEANINGS_ACTIVITY_TAG"
    }
}