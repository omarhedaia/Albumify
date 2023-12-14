package com.example.useralbumviewer.features.user_album.presentation.screens.user_profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.useralbumviewer.R

@Composable
fun AlbumsConnectionIssue(onTryAgainClicked:()->Unit){


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween,horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(id = R.drawable.photo_library),
                    contentDescription = stringResource(R.string.no_internet_image_desc),
                    modifier = Modifier.size(150.dp))

                Text(
                    text = stringResource(R.string.check_internet),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
                Button(onClick = { onTryAgainClicked() }) {
                    Text(text = stringResource(id = R.string.try_again))
                }

            }

        }
    
}

