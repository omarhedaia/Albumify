package com.example.useralbumviewer.features.user_album.data.repository

import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.RemoteSource
import com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.user.UserItem
import com.example.useralbumviewer.features.user_album.data.remote_source.user.Users
import com.example.useralbumviewer.features.user_album.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val remoteSource:RemoteSource): Repository {
    override suspend fun getAllUsers(): DataState<Users> {
        val dataState = remoteSource.getAllUsers()
        return when(dataState){
            is DataState.Error ->DataState.Error(Exception())
            is DataState.Success -> DataState.Success(dataState.data)
        }
    }

    override suspend fun getAllAlbums(): DataState<Albums> {
        val dataState = remoteSource.getAllAlbums()
        return when(dataState){
            is DataState.Error ->DataState.Error(Exception())
            is DataState.Success -> DataState.Success(dataState.data)
        }
    }

    override suspend fun getAllPhotos(): DataState<Photos> {
        val dataState = remoteSource.getAllPhotos()
        return when(dataState){
            is DataState.Error ->DataState.Error(Exception())
            is DataState.Success -> DataState.Success(dataState.data)
        }
    }

    override suspend fun getUserById(userId: Int): DataState<UserItem> {
        val dataState = remoteSource.getUserById(userId)
        return when(dataState){
            is DataState.Error ->DataState.Error(Exception())
            is DataState.Success -> DataState.Success(dataState.data)
        }
    }

    override suspend fun getAlbumByUserId(userId: Int): DataState<Albums> {
        val dataState = remoteSource.getAlbumByUserId(userId)
        return when(dataState){
            is DataState.Error ->DataState.Error(Exception())
            is DataState.Success -> DataState.Success(dataState.data)
        }
    }

    override suspend fun getPhotosByAlbumId(albumId: Int): DataState<Photos> {
        val dataState = remoteSource.getPhotosByAlbumId(albumId)
        return when(dataState){
            is DataState.Error ->DataState.Error(Exception())
            is DataState.Success -> DataState.Success(dataState.data)
        }
    }


}