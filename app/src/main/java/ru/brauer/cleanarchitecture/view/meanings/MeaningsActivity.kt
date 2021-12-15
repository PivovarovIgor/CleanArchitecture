package ru.brauer.cleanarchitecture.view.meanings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.brauer.cleanarchitecture.databinding.ActivityMeaningsBinding
import ru.brauer.appcore.model.data.DataModel
import ru.brauer.appcore.model.data.Meanings
import ru.brauer.cleanarchitecture.view.detail.DetailsActivity
import ru.brauer.cleanarchitecture.view.meanings.adapter.MeaningsAdapter

class MeaningsActivity : AppCompatActivity() {

    private val viewModel: MeaningsViewModel by viewModel()

    private val binding: ActivityMeaningsBinding by lazy {
        ActivityMeaningsBinding.inflate(layoutInflater)
    }

    private val adapter: MeaningsAdapter by lazy {
        MeaningsAdapter(object : MeaningsAdapter.OnListItemClickListener {
            override fun onItemClick(data: Meanings) {
                Intent(this@MeaningsActivity, DetailsActivity::class.java)
                    .apply {
                        putExtra(DetailsActivity.KEY_DETAIL_DATA, data)
                    }.also { startActivity(it) }
            }
        })
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