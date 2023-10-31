package com.go.marvel.utils

/**
[ResultDB] sealed class what help us to make response to view model depending of the API response
Diferents responses

@author Usiel Garcia Jimenez
 **/
sealed class ResponseAPI<T> {
    data class Success<T>(val data: T): ResponseAPI<T>()
    data class OnFailure<T>(val message: String): ResponseAPI<T>()
}