package com.go.marvel.data.remote.models

import com.go.marvel.data.local.dao.ComicDAO
import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.models.Creator
import com.go.marvel.domain.repository.IComicsRepository
import com.go.marvel.utils.BuildConfig.Companion.ERROR_DATABASE_MESSAGE
import com.go.marvel.utils.ResponseAPI
import com.go.marvel.data.local.ResultDB
import com.go.marvel.utils.toDataBase
import com.go.marvel.utils.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
[APIComicsRepository] this is the repository where all the responses of petitions are returned finally to view model.
@author Usiel Garcia Jimenez
 **/
class APIComicsRepository @Inject constructor(
    private val comicsApi: ComicService,
    private val comicDAO: ComicDAO,
) :
    IComicsRepository {
    override suspend fun getComics(): ResponseAPI<List<Comic>> {

        return try {
            val result = comicsApi.getComics()
            result.body()?.data?.results?.get(0)?.toDomain()

            ResponseAPI.Success(result.body()?.data?.results?.map {
                it.toDomain()
            } ?: emptyList())
        } catch (e: Exception) {
            ResponseAPI.OnFailure(e.message.toString())
        }

    }

    override suspend fun getCreator(comicId: Int): ResponseAPI<List<Creator>> {
        return try {
            val result = comicsApi.getCreator(comicId)
            ResponseAPI.Success(result.body()?.data?.results?.map {
                it.toDomain()
            } ?: emptyList())

        } catch (e: Exception) {
            ResponseAPI.OnFailure(e.message.toString())
        }
    }

    override suspend fun getIndividualComicById(idComic: Int): ResponseAPI<List<Comic>> {
        return try {
            val result = comicsApi.getIndividualComicById(idComic)
            ResponseAPI.Success(result.body()?.data?.results?.map {
                it.toDomain()
            } ?: emptyList())
        } catch (e: Exception) {
            ResponseAPI.OnFailure(e.message.toString())
        }
    }

    override suspend fun getSearchedComic(
        limitOfComs: Int,
        comicName: String,
    ): ResponseAPI<List<Comic>> {
        return try {
            val result = comicsApi.getComicSearched(limitOfComs, comicName)
            ResponseAPI.Success(result.body()?.data?.results?.map {
                it.toDomain()
            } ?: emptyList())
        } catch (e: Exception) {
            ResponseAPI.OnFailure(e.message.toString())
        }
    }

    override suspend fun saveComic(comic: Comic) {
        val newGame = comic.toDataBase()
        comicDAO.insert(newGame)
    }

    override suspend fun getComicsDB(): Flow<ResultDB<MutableList<Comic>>> {
        return comicDAO.getComics().map { it ->
            try {
                val newList = it.map { element ->
                    element.toDomain()
                }.toMutableList()
                ResultDB.Success(newList)
            } catch (e: Exception) {
                ResultDB.Error(message = ERROR_DATABASE_MESSAGE)
            }
        }
    }

    override suspend fun checkComicIntoDataBase(comicId: Int): Boolean {
        val result = comicDAO.getComicById(comicId)
        return result.isEmpty()
    }

    override suspend fun deleteComicIntoDataBase(comicId: Int) {
        comicDAO.delete(comicId)
    }
}