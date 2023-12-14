package com.example.useralbumviewer.features.user_album.data.remote_source

import com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.user.UserItem
import com.example.useralbumviewer.features.user_album.data.remote_source.user.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumsInterface {

    @GET("albums")
    suspend fun getAllAlbums():Response<Albums>

    @GET("users")
    suspend fun getAllUsers():Response<Users>

    @GET("photos")
    suspend fun getAllPhotos():Response<Photos>


    @GET("albums/")
    suspend fun getAlbumsByUserId(
        @Query("userId") userId:Int
    ):Response<Albums>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id:Int
    ):Response<UserItem>

    @GET("photos/")
    suspend fun getPhotosByAlbumId(
        @Query("albumId") albumId:Int
    ):Response<Photos>


}