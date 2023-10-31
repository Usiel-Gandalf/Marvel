package com.go.marvel.utils

import com.go.marvel.data.local.model.ComicEntity
import com.go.marvel.data.remote.models.comic.ComicResult
import com.go.marvel.data.remote.models.creator.CreatorResult
import com.go.marvel.data.remote.models.individualcomic.IndividualComicResult
import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.models.Creator
import com.go.marvel.utils.BuildConfig.Companion.GENERIC_IMAGE_FOR_COMICS
import com.go.marvel.utils.BuildConfig.Companion.GENERIC_IMAGE_FOR_CREATORS
import com.go.marvel.utils.BuildConfig.Companion.GENERIC_IMAGE_FOR_INDIVIDUAL_COMICS
import com.go.marvel.utils.BuildConfig.Companion.NO_TITLE_MESSAGE

/**
[ComicResult] extension function what help us to make a personalized model of comics from response comics of the API
@author Usiel Garcia Jimenez
 **/
fun ComicResult.toDomain(): Comic {
    var imageURL = GENERIC_IMAGE_FOR_COMICS
    var title = NO_TITLE_MESSAGE

    if (this.images.isNotEmpty()) {
        imageURL = this.images[0].path + "." + this.images[0].extension
    }

    if (this.title.isNotEmpty()) {
        title = this.title
    }

    return Comic(
        id = this.id,
        title = title,
        description = this.description,
        variant = this.variants,
        imageURL = (imageURL)
    )
}

fun ComicEntity.toDomain(): Comic {
    return Comic(
        id = this.id,
        title = this.title,
        description = this.description,
        imageURL = this.thumbnail
    )
}

/**
[ComicResult] extension function what help us to make a personalized model of comics from response comics of the DATABASE
@author Usiel Garcia Jimenez
 **/
fun Comic.toDataBase(): ComicEntity {
    return ComicEntity(
        id = this.id,
        title = this.title,
        description = this.description ?: "",
        thumbnail = this.imageURL ?: ""
    )
}

/**
[ComicResult] extension function what help us to make a personalized model of creators from response comics of the API
@author Usiel Garcia Jimenez
 **/
fun CreatorResult.toDomain(): Creator {
    var imageURL = GENERIC_IMAGE_FOR_CREATORS
    if (this.thumbnail.path.isNotEmpty()) {
        imageURL = this.thumbnail.path + "." + this.thumbnail.extension
    }

    return Creator(
        nameCreator = this.fullName,
        imageURL = imageURL
    )
}

/**
[ComicResult] extension function what help us to make a personalized model of comic searched from API response
@author Usiel Garcia Jimenez
 **/
fun IndividualComicResult.toDomain(): Comic {
    var imageIndividualComicURL = GENERIC_IMAGE_FOR_INDIVIDUAL_COMICS
    if (this.thumbnail.path.isNotEmpty()) {
        imageIndividualComicURL = this.thumbnail.path + "." + this.thumbnail.extension
    }

    return Comic(
        id = this.id,
        title = this.title,
        description = this.description,
        imageURL = imageIndividualComicURL
    )
}