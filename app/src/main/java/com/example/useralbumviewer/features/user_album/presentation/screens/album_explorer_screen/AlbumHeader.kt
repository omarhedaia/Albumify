package com.example.useralbumviewer.features.user_album.presentation.screens.album_explorer_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.useralbumviewer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumHeader(albumTitle:String,onQueryChanged:(String)->Unit){

    var query by remember {

        mutableStateOf("")

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        
        Text(
            text = albumTitle,
            style = MaterialTheme.typography.titleLarge
        )
        Divider(
            modifier = Modifier.padding(5.dp),
            thickness = 2.dp,
            color = Color.LightGray
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = {
                query = it
                onQueryChanged(it)
                            },
            leadingIcon = {
             Icon(Icons.Outlined.Search,
                 stringResource(R.string.search_icon_desc), tint = Color.Gray)
            }, placeholder = {
                Text(text = stringResource(R.string.search_in_images), color = Color.Gray)
            }, shape = RoundedCornerShape(15.dp),
            colors =  TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )


    }

}