package com.murzify.foodies.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkMonitor @Inject constructor(
    @ApplicationContext context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()


    suspend inline fun doOnAvailable(crossinline block: suspend () -> Unit) {
        registerNetworkCallback(
            object : NetworkCallback() {
                val scope = CoroutineScope(Dispatchers.IO)
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    scope.launch {
                        block()
                    }
                    unregisterNetworkCallback(this)
                }
            }
        )
    }

    fun registerNetworkCallback(networkCallback: NetworkCallback) {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unregisterNetworkCallback(networkCallback: NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}