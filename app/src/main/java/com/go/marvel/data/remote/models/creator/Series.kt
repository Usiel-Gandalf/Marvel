package com.go.marvel.data.remote.models.creator

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)