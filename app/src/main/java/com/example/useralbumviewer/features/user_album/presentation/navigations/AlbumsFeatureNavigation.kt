package com.example.useralbumviewer.features.user_album.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.useralbumviewer.features.user_album.presentation.screens.album_explorer_screen.AlbumExplorerScreen
import com.example.useralbumviewer.features.user_album.presentation.screens.photo_viewer_screen.PhotoViewerScreen
import com.example.useralbumviewer.features.user_album.presentation.screens.user_profile_screen.UserProfileScreen


@Composable
fun AlbumsFeatureNavigation(navController:NavHostController ) {

    NavHost(
        navController = navController,
        startDestination = Screen.UserProfile.route
    ) {

        composable(route = Screen.UserProfile.route) {
            UserProfileScreen(navController)
        }
        composable(
            route = Screen.AlbumExplorer.route,
            arguments = listOf(
                navArgument("album_id") {
                    type = NavType.IntType
                },
                navArgument("album_name") {
                    type = NavType.StringType
                }
            )
        ) {
            val albumId = it.arguments!!.getInt("album_id")
            val albumName = it.arguments!!.getString("album_name").toString()
            AlbumExplorerScreen(
                navController = navController,
                albumId = albumId,
                albumName = albumName
            )
        }

        composable(
            route = Screen.PhotoViewer.route,
            arguments = listOf(
                navArgument("photo_title") {
                    type = NavType.StringType
                },
                navArgument("photo_url") {
                    type = NavType.StringType
                }
            )
        ) {
            val photoTitle = it.arguments!!.getString("photo_title").toString()
            val photoUrl = it.arguments!!.getString("photo_url").toString()
            PhotoViewerScreen(photoTitle = photoTitle, photoUrl = photoUrl)

        }


    }
}

