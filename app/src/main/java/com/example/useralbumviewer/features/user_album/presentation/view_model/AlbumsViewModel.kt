package com.example.useralbumviewer.features.user_album.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.user.UserItem
import com.example.useralbumviewer.features.user_album.domain.usecases.GetPhotosInAlbumUseCase
import com.example.useralbumviewer.features.user_album.domain.usecases.GetUserAlbumsUseCase
import com.example.useralbumviewer.features.user_album.domain.usecases.GetUserDetailsUseCase
import com.example.useralbumviewer.features.user_album.presentation.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserAlbumsUseCase: GetUserAlbumsUseCase,
    private val getPhotosInAlbumUseCase: GetPhotosInAlbumUseCase):ViewModel() {

    var userDetailState by mutableStateOf<MainViewState<UserItem>>(MainViewState.Loading)
    var userAlbumsState by mutableStateOf<MainViewState<Albums>>(MainViewState.Loading)
    var userPhotosState by mutableStateOf<MainViewState<Photos>>(MainViewState.Loading)

    init {
        getProfile()
    }

    fun getUserAlbums(userId:Int){
        viewModelScope.launch {

            userAlbumsState = when(val albumState = getUserAlbumsUseCase(userId)){
                is DataState.Error -> MainViewState.Error(exception = albumState.exception)
                is DataState.Success -> MainViewState.Success(albumState.data)
            }

        }
    }

    fun getPhotosInAlbum(albumId:Int){
        viewModelScope.launch {
            userPhotosState = MainViewState.Loading
            userPhotosState = when(val photosState = getPhotosInAlbumUseCase(albumId)){
                is DataState.Error -> MainViewState.Error(exception = photosState.exception)
                is DataState.Success -> MainViewState.Success(photosState.data)
            }

        }
    }

    fun getProfile(){
        val randUserId = Random
        viewModelScope.launch{

            userDetailState = when(val userState = getUserDetailsUseCase(randUserId.nextInt(1,12))){
                is DataState.Error -> MainViewState.Error(exception = userState.exception)
                is DataState.Success -> {
                    MainViewState.Success(userState.data)
                }
            }

        }
    }



}