package com.go.marvel.data.remote.models

import com.go.marvel.data.remote.models.comic.ComicMarvelDTO
import com.go.marvel.data.remote.models.creator.CreatorComicDTO
import com.go.marvel.data.remote.models.individualcomic.IndividualComicMarvelDTO
import com.go.marvel.utils.BuildConfig.Companion.GENERIC_API_CREDENTIALS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
[ComicService] interface with the contracts what we uses for make diferents petitions depending of our necesities
@author Usiel Garcia Jimenez
 **/
interface ComicService {

    @GET("comics?$GENERIC_API_CREDENTIALS")
    suspend fun getComics(): Response<ComicMarvelDTO>

    @GET("comics/{comicId}/creators?$GENERIC_API_CREDENTIALS")
    suspend fun getCreator(@Path("comicId") comicId: Int): Response<CreatorComicDTO>

    @GET("comics/{comicId}?$GENERIC_API_CREDENTIALS")
    suspend fun getIndividualComicById(@Path("comicId") comicId: Int): Response<IndividualComicMarvelDTO>

    @GET("characters?$GENERIC_API_CREDENTIALS")
    suspend fun getComicSearched(@Query("limit") limit: Int, @Query("nameStartsWith") name: String): Response<IndividualComicMarvelDTO>

}