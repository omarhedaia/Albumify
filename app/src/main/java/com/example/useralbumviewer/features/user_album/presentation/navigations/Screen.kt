package com.example.useralbumviewer.features.user_album.presentation.navigations

sealed class Screen(val route:String){
    object UserProfile:Screen(route = "user_profile")
    object AlbumExplorer:Screen(route = "album_explorer/{album_id}/{album_name}"){
        fun passAlbumIdAndName(albumId:Int,albumName:String):String{
            return "album_explorer/$albumId/$albumName"
        }
    }
    object PhotoViewer:Screen(route = "photo_viewer/{photo_title}/{photo_url}"){
        fun passPhotoDetails(title:String,photoUrl:String):String{
            return "photo_viewer/$title/$photoUrl"

        }
    }
}
