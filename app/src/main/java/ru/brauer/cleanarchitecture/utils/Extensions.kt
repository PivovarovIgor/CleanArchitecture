package ru.brauer.cleanarchitecture.utils

import android.net.Uri

private const val SCHEME_OF_URL_IMAGE = "https"

fun String.toUrl(): Uri = Uri.Builder()
    .scheme(SCHEME_OF_URL_IMAGE)
    .encodedPath(this)
    .build()