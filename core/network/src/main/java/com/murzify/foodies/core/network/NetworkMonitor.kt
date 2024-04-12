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
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkMonitor @Inject constructor(
    @ApplicationContext context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()


    suspend fun <T> doOnAvailable(block: suspend () -> T): T = suspendCoroutine { continuation ->
        registerNetworkCallback(
            object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    CoroutineScope(Dispatchers.IO).launch {
                        continuation.resume(block())
                    }
                    unregisterNetworkCallback(this)
                }
            }
        )
    }


    private fun registerNetworkCallback(networkCallback: NetworkCallback) {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unregisterNetworkCallback(networkCallback: NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}