package com.go.marvel.data.remote.models.individualcomic

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)