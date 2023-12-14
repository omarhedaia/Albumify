package com.example.useralbumviewer.features.user_album.presentation.screens.album_explorer_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.PhotosItem
import com.example.useralbumviewer.features.user_album.presentation.MainViewState
import com.example.useralbumviewer.features.user_album.presentation.navigations.Screen
import com.example.useralbumviewer.features.user_album.presentation.view_model.AlbumsViewModel
import com.example.useralbumviewer.util.ConnectionState
import com.example.useralbumviewer.util.currentConnectivityState
import com.example.useralbumviewer.util.screens.UnAvailableInternetScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AlbumExplorerScreen(
    navController: NavHostController,
    vm:AlbumsViewModel = hiltViewModel(),
    albumId:Int,
    albumName:String
){

    val context = LocalContext.current
    var photosList by rememberSaveable {
        mutableStateOf(listOf<PhotosItem>())
    }
    var filteredPhotosList by rememberSaveable(photosList) {
        mutableStateOf(photosList)
    }
    var connectivityState by remember {
        mutableStateOf(context.currentConnectivityState)
    }



    when(connectivityState){
        ConnectionState.Available -> {

            LaunchedEffect(key1 = true){
                vm.getPhotosInAlbum(albumId)
            }

            val photosState = vm.userPhotosState
            
            Column(modifier = Modifier.fillMaxSize()) {

                AlbumHeader(albumTitle = albumName){query ->

                    if (query.isNotEmpty())
                    {
                        filteredPhotosList = photosList.filter { photo ->
                            photo.title.contains(query)
                        }
                    }else
                    {
                        filteredPhotosList = photosList
                    }

                }

                when(photosState){
                    is MainViewState.Error -> {
                        AlbumExplorerConnectionIssue(){
                            vm.getPhotosInAlbum(albumId)
                        }
                    }
                    MainViewState.Loading ->
                    {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                            CircularProgressIndicator()
                        }
                    }
                    is MainViewState.Success -> {

                        LaunchedEffect(key1 = true){
                            photosList = photosState.data
                        }
                        AlbumContent(modifier = Modifier, photos = filteredPhotosList){
                                clickedPhoto ->
                            val encodedUrl = URLEncoder.encode(clickedPhoto.url, StandardCharsets.UTF_8.toString())
                            navController.navigate(Screen.PhotoViewer.passPhotoDetails(clickedPhoto.title,encodedUrl))

                        }

                    }
                }
                

            }

        }
        ConnectionState.Unavailable -> {
            UnAvailableInternetScreen(){
                connectivityState = context.currentConnectivityState
            }
        }
    }




}