package com.example.useralbumviewer.features.user_album.presentation.screens.photo_viewer_screen

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.useralbumviewer.R
import com.example.useralbumviewer.util.ConnectionState
import com.example.useralbumviewer.util.currentConnectivityState
import com.example.useralbumviewer.util.screens.UnAvailableInternetScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoViewerScreen(photoTitle:String,photoUrl:String){


    val context = LocalContext.current
    var scale by remember {
        mutableStateOf(1f)
    }
    var photoBitMap by remember{
        mutableStateOf<Bitmap?>(null)
    }
    var connectivityState by remember {
        mutableStateOf(context.currentConnectivityState)
    }

    val loadPhotoCoroutine = rememberCoroutineScope()

    when(connectivityState){
        ConnectionState.Available -> {

            Card(
                modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            ) {
            Column(
                modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                    colors = CardDefaults.elevatedCardColors()
                    ) {

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = photoTitle,
                        style = MaterialTheme.typography.titleLarge
                    )
                }


                Box {

                        GlideImage(
                            model = photoUrl,
                            contentDescription = stringResource(R.string.photo_desc),
                            alignment = Alignment.Center,
                            loading = placeholder(R.drawable.baseline_downloading_24),
                            transition = CrossFade,
                            modifier = Modifier
                                .graphicsLayer(
                                    scaleX = scale,
                                    scaleY = scale
                                )
                                .pointerInput(Unit) {
                                    detectTransformGestures { _, _, zoom, _ ->
                                        scale = when {
                                            scale < 0.5f -> 0.5f
                                            scale > 3f -> 3f
                                            else -> scale * zoom
                                        }

                                    }
                                },
                        ){
                            loadPhotoCoroutine.launch {

                                photoBitMap =
                                    withContext(Dispatchers.IO) {
                                        it.submit().get(3L, TimeUnit.SECONDS)
                                    }.toBitmap()
                            }

                            it

                        }
                    }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        sharePhoto(context,photoBitMap)
                    }) {
                    Text(
                        text = stringResource(R.string.share_icon_title),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = stringResource(R.string.share_icon)
                    )
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

fun sharePhoto(context: Context, photoBitMap: Bitmap?) {

    if (photoBitMap!=null)
    {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        val path = MediaStore.Images.Media.insertImage(context.contentResolver,photoBitMap,
            context.getString(
                R.string.image
            ),null)
        val uri = Uri.parse(path)
        intent.putExtra(Intent.EXTRA_STREAM,uri)
        intent.type = "image/*"
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_to)))
    }


}
