package com.example.useralbumviewer.features.user_album.domain.usecases

import com.example.useralbumviewer.features.user_album.DataState
import com.example.useralbumviewer.features.user_album.data.remote_source.user.UserItem
import com.example.useralbumviewer.features.user_album.domain.repository.Repository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: Repository){

    suspend operator fun invoke(id:Int):DataState<UserItem>{
        return repository.getUserById(id)
    }

}