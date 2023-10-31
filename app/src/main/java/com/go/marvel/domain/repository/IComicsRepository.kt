package com.go.marvel.domain.repository

import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.models.Creator
import com.go.marvel.utils.ResponseAPI
import com.go.marvel.data.local.ResultDB
import kotlinx.coroutines.flow.Flow

/**
[IComicsRepository] interface what have all the contracts for make diferents actions with petition from the view model
all the functions listed below make a function in specific with petition from the view model

@author Usiel Garcia Jimenez
 **/
interface IComicsRepository {
    //funcion que devuelve toda la lista de comics que vienen de la peticion
    suspend fun getComics(): ResponseAPI<List<Comic>>

    //funcion que obtiene al creador de un comic en especifico mediante el id del comic
    suspend fun getCreator(comicId: Int): ResponseAPI<List<Creator>>

    //funcion que obtiene un comic en especifico mediante el nombre, funcion buscar
    suspend fun getSearchedComic(limitOfComics: Int, comicName: String): ResponseAPI<List<Comic>>

    //funcion que regresa un comic en especifico mediante el id del comic
    suspend fun getIndividualComicById(idComic: Int): ResponseAPI<List<Comic>>

    //funcion que guarda un comic en la base de datos
    suspend fun saveComic(comic: Comic)

    //funcion que regresa toda la lista de comics guardado en la base de datos
    suspend fun getComicsDB(): Flow<ResultDB<MutableList<Comic>>>

    //funcion para comprobar si un comic ya esta registrado en la BD
    suspend fun checkComicIntoDataBase(comicId: Int): Boolean

    //funcion que borra un comic si ya esta registrado en la base de datos mediante su id
    suspend fun deleteComicIntoDataBase(comicId: Int)
}