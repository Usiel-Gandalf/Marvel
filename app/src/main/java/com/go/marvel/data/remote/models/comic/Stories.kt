package com.go.marvel.data.remote.models.comic

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)