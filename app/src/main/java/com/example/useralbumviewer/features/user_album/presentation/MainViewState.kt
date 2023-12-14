package com.example.useralbumviewer.features.user_album.presentation

import java.lang.Exception

sealed class MainViewState<out O>{
    data class Success<T>(var data:T):MainViewState<T>()
    object Loading: MainViewState<Nothing>()
    data class Error <out T>(val exception: Exception):MainViewState<T>()
}
