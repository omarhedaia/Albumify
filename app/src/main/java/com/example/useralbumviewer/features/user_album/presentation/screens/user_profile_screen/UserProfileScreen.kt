package com.example.useralbumviewer.features.user_album.presentation.screens.user_profile_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.useralbumviewer.features.user_album.data.remote_source.user.Address
import com.example.useralbumviewer.features.user_album.presentation.MainViewState
import com.example.useralbumviewer.features.user_album.presentation.navigations.Screen
import com.example.useralbumviewer.features.user_album.presentation.view_model.AlbumsViewModel
import com.example.useralbumviewer.util.ConnectionState
import com.example.useralbumviewer.util.currentConnectivityState
import com.example.useralbumviewer.util.screens.UnAvailableInternetScreen

@Composable
fun UserProfileScreen(navHostController: NavHostController,vm:AlbumsViewModel = hiltViewModel()){

    val userState =vm.userDetailState
    val context = LocalContext.current
    var connectivityState by remember {
        mutableStateOf(context.currentConnectivityState)
    }

    when(connectivityState){
        ConnectionState.Available -> {

            when(userState){
                is MainViewState.Error -> {
                    ProfileConnectionIssue(){
                        vm.getProfile()
                    }
                }
                MainViewState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                        CircularProgressIndicator()
                    }
                }
                is MainViewState.Success -> {

                    val userId = userState.data.id
                    val userName = userState.data.name
                    val userAddress = formatUserAddress(userState.data.address)
                    val albumState = vm.userAlbumsState


                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)) {

                        ProfileHeader(userName,userAddress)

                        LaunchedEffect(true){
                            vm.getUserAlbums(userId)
                        }


                        when(albumState){
                            is MainViewState.Error -> {
                                AlbumsConnectionIssue(){
                                    vm.getUserAlbums(userId)
                                }
                            }
                            MainViewState.Loading -> {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                                    CircularProgressIndicator()
                                }
                            }
                            is MainViewState.Success -> {

                                ProfileAlbumsList(albums = albumState.data){ id,title ->
                                    navHostController.navigate(Screen.AlbumExplorer.passAlbumIdAndName(id,title))
                                }


                            }

                            else -> {}
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

fun formatUserAddress(address: Address):String{
    val userAddress = StringBuilder()
    userAddress.append(address.city)
    userAddress.append(", ")
    userAddress.append(address.geo)
    userAddress.append(", ")
    userAddress.append(address.street)
    userAddress.append(", ")
    userAddress.append(address.suite)
    userAddress.append(", ")
    userAddress.append(address.zipcode)

    return userAddress.toString()
}