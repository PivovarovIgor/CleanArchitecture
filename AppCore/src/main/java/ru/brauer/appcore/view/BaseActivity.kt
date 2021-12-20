package ru.brauer.appcore.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    private val onLineLiveData: OnLineLiveData by inject()
    private var snackbar: Snackbar? = null
    
    override fun onResume() {
        super.onResume()
        onLineLiveData.observe(this, ::renderData)
    }

    private fun renderData(isOnLine: Boolean) {
        if (isOnLine) {
            snackbar?.run {
                dismiss()
                snackbar = null
            }
        } else {
            if (snackbar?.isShown == true) return
            snackbar = Snackbar.make(
                this,
                window.decorView.rootView,
                "Network connectivity is lost.",
                Snackbar.LENGTH_INDEFINITE
            ).apply { show() }
        }
    }
}