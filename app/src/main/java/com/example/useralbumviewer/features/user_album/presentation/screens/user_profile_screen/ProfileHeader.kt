package com.example.useralbumviewer.features.user_album.presentation.screens.user_profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.useralbumviewer.R

@Composable
fun ProfileHeader(
    userName:String,
    userAddress:String
) {
    Column {

        Text(
            text = stringResource(R.string.profile),
            style = MaterialTheme.typography.titleLarge
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)) {

            Column {

                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = userAddress)
            }
        }

        Text(
            text = stringResource(R.string.my_albums),
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.titleMedium
        )

    }

}