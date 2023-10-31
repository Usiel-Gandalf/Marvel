package com.go.marvel.ui.detail

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.models.Creator
import com.go.marvel.domain.repository.IComicsRepository
import com.go.marvel.utils.ResponseAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailComicViewModel @Inject constructor(
    private val apiRepository: IComicsRepository
) : ViewModel(){

    private val _individualComicIdSelectedForDetail = mutableStateOf(0)

    lateinit var comic: Comic
        private set

    var individualComicList: MutableList<Comic> = mutableStateListOf()
        private set

    var comicStatusIntoDataBase = mutableStateOf(false)
        private set

    var creatorList: MutableList<Creator> = mutableStateListOf()
        private set

    fun setComicForDetail(comic: Comic, idComic: Int){
        this.comic = Comic(id = comic.id, title = comic.title, imageURL = comic.imageURL, variant = comic.variant)
        getCreatorList(idComic)
        _individualComicIdSelectedForDetail.value = idComic

    }

    /**
    [getCreatorList] is the function what make a petition of creators of comics with a comic id
    [idComic]: id for the comic for search at it creator
    Function for make a search and petition of creators with a id of the comic

    @author Usiel Garcia Jimenez
     **/
    private fun getCreatorList(idComic: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = apiRepository.getCreator(_individualComicIdSelectedForDetail.value)) {
                is ResponseAPI.OnFailure -> {
                    Log.d("Error", result.message)
                }

                is ResponseAPI.Success -> {
                    withContext(Dispatchers.Main) {
                        creatorList.clear()
                        creatorList.addAll(result.data)
                    }
                }
            }
        }
    }

    /**
    [searchComic] function what make a comprobation of one comic into database with their id, this is for add or remove a comic from our favorites comic list
    Function for comprobate the status of comic in our favorite list

    @author Usiel Garcia Jimenez
     **/
    fun getStatusComicIntoDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = apiRepository.checkComicIntoDataBase(_individualComicIdSelectedForDetail.value)
            withContext(Dispatchers.Main) {
                comicStatusIntoDataBase.value = result
            }
        }
    }

    /**
    [addComicToFavorites] function what save a comic into database in our favorites comic list with a object
    Function for save a comic in our favorite list in the database

    @author Usiel Garcia Jimenez
     **/
    fun addComicToFavorites(comic: Comic){
        viewModelScope.launch {
            apiRepository.saveComic(comic)
            getStatusComicIntoDataBase()
        }
    }

    /**
    [removeComicFromFavorites] function what remove one comic into database in our favorites comic list with a id
    Function for delete a comic in our favorite list in the database

    @author Usiel Garcia Jimenez
     **/
    fun removeComicFromFavorites(comic: Comic){
        viewModelScope.launch {
            apiRepository.deleteComicIntoDataBase(comic.id)
            getStatusComicIntoDataBase()
        }
    }
}