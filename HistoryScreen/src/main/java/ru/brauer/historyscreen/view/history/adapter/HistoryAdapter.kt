package ru.brauer.historyscreen.view.history.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.brauer.historyscreen.databinding.ActivityHistoryRecyclerviewItemBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var data: List<ru.brauer.historyscreen.datasource.database.SearchWord> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ActivityHistoryRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.count()

    inner class ViewHolder(private val binding: ActivityHistoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchWord: ru.brauer.historyscreen.datasource.database.SearchWord) {
            binding.dateTime.text = searchWord.dateTime.toString()
            binding.searchWord.text = searchWord.searchWord
        }
    }
}