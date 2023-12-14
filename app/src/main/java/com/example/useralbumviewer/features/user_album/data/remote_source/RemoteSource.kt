package com.example.useralbumviewer.features.user_album.data.remote_source

import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.user.UserItem
import com.example.useralbumviewer.features.user_album.data.remote_source.user.Users
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val albumsInterface: AlbumsInterface) {

    suspend fun getAllUsers():DataState<Users>{
        return try {

            val body = albumsInterface.getAllUsers().body()
            if (body!= null){
                DataState.Success(body)
            }else{
                DataState.Error(NullPointerException())
            }
        }catch (e:Exception){
            DataState.Error(e)
        }

    }

    suspend fun getAllAlbums():DataState<Albums>{
        return try {

            val body = albumsInterface.getAllAlbums().body()
            if (body!= null){
                DataState.Success(body)
            }else{
                DataState.Error(NullPointerException())
            }
        }catch (e:Exception){
            DataState.Error(e)
        }

    }

    suspend fun getAllPhotos():DataState<Photos>{
        return try {

            val body = albumsInterface.getAllPhotos().body()
            if (body!= null){
                DataState.Success(body)
            }else{
                DataState.Error(NullPointerException())
            }
        }catch (e:Exception){
            DataState.Error(e)
        }

    }

    suspend fun getUserById(id:Int):DataState<UserItem>{
        return try {

            val body = albumsInterface.getUserById(id).body()
            if (body!= null){
                DataState.Success(body)
            }else{
                DataState.Error(NullPointerException())
            }
        }catch (e:Exception){
            DataState.Error(e)
        }

    }

    suspend fun getAlbumByUserId(userId:Int):DataState<Albums>{
        return try {

            val body = albumsInterface.getAlbumsByUserId(userId).body()
            if (body!= null){
                DataState.Success(body)
            }else{
                DataState.Error(NullPointerException())
            }
        }catch (e:Exception){
            DataState.Error(e)
        }

    }

    suspend fun getPhotosByAlbumId(albumId:Int):DataState<Photos>{
        return try {

            val body = albumsInterface.getPhotosByAlbumId(albumId).body()
            if (body!= null){
                DataState.Success(body)
            }else{
                DataState.Error(NullPointerException())
            }
        }catch (e:Exception){
            DataState.Error(e)
        }

    }




}