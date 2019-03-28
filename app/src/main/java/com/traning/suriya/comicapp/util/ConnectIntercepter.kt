package com.traning.suriya.comicapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class ConnectIntercepter(context: Context): Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) throw NoInternetException()
        return chain.proceed(chain.request())
    }

    @SuppressLint("ServiceCast")
    private fun isOnline(): Boolean{
        val connectInternet = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val neteworkInfo = connectInternet.activeNetworkInfo
        return neteworkInfo != null && neteworkInfo.isConnected
    }
}