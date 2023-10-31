package com.go.marvel.data.remote.models.individualcomic

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)