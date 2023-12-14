package com.example.useralbumviewer.features.user_album.presentation.screens.user_profile_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.useralbumviewer.features.user_album.data.remote_source.album.Albums
import com.example.useralbumviewer.features.user_album.presentation.navigations.Screen

@Composable
fun ProfileAlbumsList(albums: Albums,onAlbumClick:(Int,String)->Unit){
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .padding(top = 5.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(albums){album ->
            Divider()
            Box(modifier = Modifier.fillMaxWidth().clickable {
                onAlbumClick(album.id,album.title)
            }.padding(vertical = 10.dp)){
                Text(text = album.title)
            }

        }
    }
}