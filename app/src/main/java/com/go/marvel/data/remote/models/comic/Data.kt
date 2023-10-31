package com.go.marvel.data.remote.models.comic

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicResult>,
    val total: Int
)