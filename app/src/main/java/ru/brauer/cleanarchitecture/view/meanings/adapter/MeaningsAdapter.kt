package ru.brauer.cleanarchitecture.view.meanings.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.brauer.cleanarchitecture.databinding.ActiviyMeaningsRecyclerviewItemBinding
import ru.brauer.cleanarchitecture.model.data.Meanings

class MeaningsAdapter : RecyclerView.Adapter<MeaningsAdapter.ViewHolder>() {

    private var data: List<Meanings>? = null

    fun setData(data: List<Meanings>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ActiviyMeaningsRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = data?.count() ?: 0

    inner class ViewHolder(private val binding: ActiviyMeaningsRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meanings: Meanings) {
            with(binding) {
                meanings.translation
                    ?.let {
                        text.text = it.translation
                        note.text = it.note
                    }
                Glide.with(image)
                    .load(Uri.Builder()
                        .scheme("https")
                        .encodedPath(meanings.imageUrl)
                        .build())
                    .into(image)
            }
        }
    }
}