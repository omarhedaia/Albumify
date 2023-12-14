package com.example.useralbumviewer.util

sealed class ConnectionState{
    object Available:ConnectionState()
    object Unavailable:ConnectionState()
}
