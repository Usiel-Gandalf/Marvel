package com.go.marvel.data.remote.models.individualcomic

data class IndividualComicMarvelDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)