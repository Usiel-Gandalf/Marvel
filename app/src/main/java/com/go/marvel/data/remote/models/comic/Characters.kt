package com.go.marvel.data.remote.models.comic

import android.content.ClipData

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<ClipData.Item>,
    val returned: Int
)