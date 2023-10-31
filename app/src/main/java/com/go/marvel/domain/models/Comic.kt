package com.go.marvel.domain.models

import com.go.marvel.data.remote.models.comic.Variant

/**
[Comic] data class of the Comic for our petition, with this model we work in our ui layer
Model of creator

@author Usiel Garcia Jimenez
 **/
data class Comic(
    var id: Int,
    var title: String,
    var description: String? = "",
    var imageURL: String? = "",
    var variant: List<Variant?> = emptyList(),
)