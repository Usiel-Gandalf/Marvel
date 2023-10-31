package com.go.marvel.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.repository.IComicsRepository
import com.go.marvel.data.local.ResultDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
[FavoritesComicView] view model for the favorites view where all our comics added like favorites in our favorites list
[apiRepository] repository injected, this repository make the logic for this view model
view model for favorites view

@author Usiel Garcia Jimenez
 **/
@HiltViewModel
class FavoritesComicViewModel @Inject constructor(private val apiRepository: IComicsRepository) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _comicFavoritesList = MutableStateFlow<List<Comic>>(emptyList())
    val comicFavoritesList: StateFlow<List<Comic>> = _comicFavoritesList

    private var _jobList: Job? = null

    init {
        _jobList = getListFlowDB()
    }


    /**
    [getListFlowDB] function what get all the comics what we have in our list of favorites comics
    Function for make a petition for get all our favorites comics in a list

    @author Usiel Garcia Jimenez
     **/
    private fun getListFlowDB(): Job {
        _loading.value = true
        _error.value = false
        return viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getComicsDB().catch {
                _error.postValue(true)
            }.collect { resultFlow ->
                when (resultFlow) {
                    is ResultDB.Error -> {
                        _error.postValue(true)
                    }
                    is ResultDB.Success -> {
                        _loading.postValue(false)
                        _error.postValue(false)
                        _comicFavoritesList.value = resultFlow.data
                    }
                }
            }
        }
    }
}