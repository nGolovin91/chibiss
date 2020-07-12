package com.yoite.chibissapp.model.api.common.http

import okhttp3.OkHttpClient


interface HttpClientProvider {

    val httpClient: OkHttpClient

    fun destroy()

}