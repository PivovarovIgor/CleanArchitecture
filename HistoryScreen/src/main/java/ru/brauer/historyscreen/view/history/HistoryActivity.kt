package ru.brauer.historyscreen.view.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope
import ru.brauer.appcore.view.BaseActivity
import ru.brauer.historyscreen.databinding.ActivityHistoryBinding
import ru.brauer.historyscreen.view.history.adapter.HistoryAdapter

class HistoryActivity : BaseActivity() {

    private val scope: Scope by lazy {
        getKoin().getOrCreateScope<HistoryActivity>("SearchHistory")
    }

    private val viewModel: HistoryViewModel by scope.inject()
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

    private fun renderData(historySearch: List<ru.brauer.historyscreen.datasource.database.SearchWord>) {
        adapter.data = historySearch
    }

    override fun onBackPressed() {
        super.onBackPressed()
        scope.close()
    }
}