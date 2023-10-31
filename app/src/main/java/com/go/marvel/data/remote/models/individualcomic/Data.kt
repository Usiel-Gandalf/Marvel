package com.go.marvel.data.remote.models.individualcomic

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<IndividualComicResult>,
    val total: Int
)