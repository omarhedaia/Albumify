package com.example.useralbumviewer.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

val Context.currentConnectivityState : ConnectionState
    get() {
        val connectivityyManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityyManager)
    }

fun getCurrentConnectivityState(connectivityyManager: ConnectivityManager): ConnectionState {

    val connected = connectivityyManager.allNetworks.any {network ->
        connectivityyManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?:false
    }
    return if (connected){
        ConnectionState.Available
    }else{
        ConnectionState.Unavailable
    }
}
