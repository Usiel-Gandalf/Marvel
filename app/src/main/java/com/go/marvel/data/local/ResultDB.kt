package com.go.marvel.data.local

/**
[ResultDB] sealed class what help us to make response to view model depending of the DataBase response
Diferents responses

@author Usiel Garcia Jimenez
 **/
sealed class ResultDB<T> {
    data class Success<T>(val data : T) : ResultDB<T>()
    data class Error<T>(val message  : String) : ResultDB<T>()
}