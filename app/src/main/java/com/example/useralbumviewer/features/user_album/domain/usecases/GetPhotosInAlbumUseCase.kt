package com.example.useralbumviewer.features.user_album.domain.usecases

import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.domain.repository.Repository
import javax.inject.Inject

class GetPhotosInAlbumUseCase @Inject constructor(
    private val repository: Repository) {

    suspend operator fun invoke(albumId:Int):DataState<Photos>{
        return repository.getPhotosByAlbumId(albumId)
    }

}