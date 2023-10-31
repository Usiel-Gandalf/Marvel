package com.go.marvel.data.remote.models.comic

import com.google.gson.annotations.SerializedName

data class ComicDTOItem (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)