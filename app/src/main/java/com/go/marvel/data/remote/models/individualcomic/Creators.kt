package com.go.marvel.data.remote.models.individualcomic

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)