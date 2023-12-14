package com.example.useralbumviewer.features.user_album.domain.usecases

import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums
import com.example.useralbumviewer.features.user_album.domain.repository.Repository
import javax.inject.Inject

class GetUserAlbumsUseCase @Inject constructor(
    private val repository: Repository) {

    suspend operator fun invoke(userId:Int):DataState<Albums>{
        return repository.getAlbumByUserId(userId)
    }

}