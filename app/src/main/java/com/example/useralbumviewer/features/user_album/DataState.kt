package com.example.useralbumviewer.features.user_album

sealed class DataState<out O> {

    data class Success<out T> (val data:T) : DataState<T>()
    data class Error (val exception: kotlin.Exception) : DataState<Nothing>()

}
