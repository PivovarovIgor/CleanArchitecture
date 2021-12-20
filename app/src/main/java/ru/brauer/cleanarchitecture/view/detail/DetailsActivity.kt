package ru.brauer.cleanarchitecture.view.detail

import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import ru.brauer.appcore.model.data.Meanings
import ru.brauer.appcore.view.BaseActivity
import ru.brauer.cleanarchitecture.databinding.ActivityDetailBinding

class DetailsActivity : BaseActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getIntent()
            ?.getParcelableExtra<Meanings>(KEY_DETAIL_DATA)
            ?.also {
                Glide.with(binding.image)
                    .load(
                        Uri.Builder()
                        .scheme(SCHEME_OF_URL_IMAGE)
                        .encodedPath(it.imageUrl)
                        .build())
                    .into(binding.image)
                binding.text.text = it.translation?.translation
                binding.note.text = it.translation?.note
            }

    }
    companion object {
        const val KEY_DETAIL_DATA = "KEY_DETAIL_DATA"
        private const val SCHEME_OF_URL_IMAGE = "https"
    }
}