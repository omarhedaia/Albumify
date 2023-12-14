package com.example.useralbumviewer.features.user_album.data.remote_source.photo

data class PhotosItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)