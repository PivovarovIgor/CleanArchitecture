package ru.brauer.cleanarchitecture.view.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.brauer.cleanarchitecture.databinding.ActivityHistoryBinding
import ru.brauer.cleanarchitecture.model.datasource.database.SearchWord
import ru.brauer.cleanarchitecture.view.history.adapter.HistoryAdapter

class HistoryActivity : AppCompatActivity() {

    private val viewModel: HistoryViewModel by viewModel()
    private val adapter: HistoryAdapter = HistoryAdapter()

    private val binding: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.listOfHistory) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HistoryActivity.adapter
        }

        viewModel.getData().observe(this, ::renderData)
    }

    private fun renderData(historySearch: List<SearchWord>) {
        adapter.data = historySearch
    }
}