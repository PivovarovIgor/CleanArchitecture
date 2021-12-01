package ru.brauer.cleanarchitecture.view.meanings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.brauer.cleanarchitecture.App
import ru.brauer.cleanarchitecture.databinding.ActivityMeaningsBinding
import ru.brauer.cleanarchitecture.di.viewmodel.ViewModelFactory
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.data.Meanings
import ru.brauer.cleanarchitecture.view.meanings.adapter.MeaningsAdapter
import javax.inject.Inject

class MeaningsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MeaningsViewModel by lazy {
        ViewModelProvider(
            this@MeaningsActivity,
            viewModelFactory,
        ).get(MeaningsViewModel::class.java)
    }

    private val binding: ActivityMeaningsBinding by lazy {
        ActivityMeaningsBinding.inflate(layoutInflater)
    }

    private val adapter: MeaningsAdapter by lazy {
        MeaningsAdapter()
    }

    private val dataModel: DataModel by lazy {
        requireNotNull(intent.getParcelableExtra(MEANINGS_ACTIVITY_TAG))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        App.instance.appComponent.inject(this)
        with(binding.recyclerviewMeanings) {
            layoutManager = LinearLayoutManager(this@MeaningsActivity)
            adapter = this@MeaningsActivity.adapter
        }
        with(viewModel) {
            liveData.observe(this@MeaningsActivity, ::renderData)
            getData(dataModel)
        }
    }

    private fun renderData(appState: List<Meanings>) {
        adapter.setData(appState)
    }

    companion object {
        const val MEANINGS_ACTIVITY_TAG = "MEANINGS_ACTIVITY_TAG"
    }
}