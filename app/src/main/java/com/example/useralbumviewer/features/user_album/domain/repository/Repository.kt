package com.example.useralbumviewer.features.user_album.domain.repository

import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.user.UserItem
import com.example.useralbumviewer.features.user_album.data.remote_source.user.Users

interface Repository {

    suspend fun getAllUsers():DataState<Users>
    suspend fun getAllAlbums(): DataState<com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums>
    suspend fun getAllPhotos():DataState<Photos>

    suspend fun getUserById(userId:Int):DataState<UserItem>
    suspend fun getAlbumByUserId(userId: Int): DataState<com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums>
    suspend fun getPhotosByAlbumId(albumId:Int):DataState<Photos>



}