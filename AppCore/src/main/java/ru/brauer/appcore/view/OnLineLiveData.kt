package ru.brauer.appcore.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class OnLineLiveData(context: Context) : LiveData<Boolean>() {

    private val availableNetworks = mutableSetOf<Network>()

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val request: NetworkRequest = NetworkRequest.Builder().build()

    private val callback = object : ConnectivityManager.NetworkCallback() {

        override fun onLost(network: Network) {
            availableNetworks.remove(network)
            update(availableNetworks.isNotEmpty())
        }

        override fun onAvailable(network: Network) {
            availableNetworks.add(network)
            update(availableNetworks.isNotEmpty())
        }
    }

    init {
        update(false)
        connectivityManager.registerNetworkCallback(request, callback)
    }

    private fun update(online: Boolean) {
        if (online != value) {
            postValue(online)
        }
    }
}