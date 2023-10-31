package com.go.marvel.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.go.marvel.domain.models.Comic
import com.go.marvel.domain.repository.IComicsRepository
import com.go.marvel.utils.ResponseAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
[ComicViewModel] is the main view model what implemnts HiltViewModel
[apiRepository]: is the repository injected for the comunication with domain layer
This is the main view model what implements almost all the logic for the app

@author Usiel Garcia Jimenez
 **/
@HiltViewModel
class ComicViewModel @Inject constructor(
    private val apiRepository: IComicsRepository,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    var comicList: MutableList<Comic> = mutableStateListOf()
        private set

    var filterComicList: MutableList<Comic> = mutableStateListOf()
        private set

    private val _search = mutableStateOf("")
    val search: State<String> get() = _search

    /**
    [getComicList] is the function what make the petition for can get the list of the comics what are show it in the main view
    This is the fun for get the list of comics

    @author Usiel Garcia Jimenez
     **/
    fun getComicList() {
        _loading.value = true
        _error.value = false

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = apiRepository.getComics()) {
                is ResponseAPI.OnFailure -> {
                    _error.postValue(true)
                }

                is ResponseAPI.Success -> {
                    withContext(Dispatchers.Main) {
                        _loading.postValue(false)
                        comicList.clear()
                        comicList.addAll(result.data)
                        filterComicList.clear()
                        filterComicList.addAll(result.data)
                    }
                }
            }
        }
    }

    /**
    [updateSearchText] function what update the state od the searched text
    [text]: is the value what the user insert into the search text field
    Function for update in real time the text what we are introducing in the text field

    @author Usiel Garcia Jimenez
     **/
    fun updateSearchText(text: CharSequence?) {
        _search.value = text.toString()
    }

    /**
    [searchComic] function what when we introduces a text into search text field, it is searched into comic list
    Function for search one especific comic introduced into search textfield

    @author Usiel Garcia Jimenez
     **/
    fun searchComic() {
        if (search.value.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    filterComicList.clear()
                    filterComicList.addAll(comicList.filter { comic ->
                        //comic.title.trim().equals(_search.value.trim(), ignoreCase = true)
                        comic.title.startsWith(_search.value[0])
                    })
                }
            }
        } else {
            filterComicList.addAll(comicList)
        }
    }
}