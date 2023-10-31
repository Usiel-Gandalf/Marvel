package com.go.marvel.data.remote.models.creator

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)