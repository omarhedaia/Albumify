package com.example.useralbumviewer.features.user_album.presentation.screens.album_explorer_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.useralbumviewer.R
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.Photos
import com.example.useralbumviewer.features.user_album.data.remote_source.photo.PhotosItem
import com.example.useralbumviewer.features.user_album.presentation.view_model.AlbumsViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AlbumContent(modifier:Modifier,photos: List<PhotosItem>,onImageClick:(PhotosItem)->Unit){
    
    LazyVerticalGrid(modifier = modifier.fillMaxWidth(),columns = GridCells.Adaptive(100.dp), horizontalArrangement = Arrangement.Center)
    {
        items(photos){photo ->
            
            Box(modifier = Modifier.aspectRatio(1f)){

                GlideImage(
                    model = photo.url,
                    contentDescription = stringResource(R.string.image_view),
                    loading = placeholder(R.drawable.baseline_downloading_24),
                    modifier = Modifier.clickable {
                    onImageClick(photo)
                })

            }
            
            
        }

    }
    
    
    
}