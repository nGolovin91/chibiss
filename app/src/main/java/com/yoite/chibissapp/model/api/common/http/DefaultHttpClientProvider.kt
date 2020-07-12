package com.yoite.chibissapp.model.api.common.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class DefaultHttpClientProvider @Inject constructor(
    private val defaultInterceptor: Interceptor
) : HttpClientProvider {

    companion object {
        const val DEFAULT_QUALIFIER = "0f693ba3-1621-4bf1-a6b9-b173b0f9a3d8"

        private const val TIME_OUT_CALL = 10L
        private const val TIME_OUT_CONNECT = 10L
        private const val TIME_OUT_WRITE = 10L
        private const val TIME_OUT_READ = 0L
    }

    private val timeOutConfig: TimeOutConfig? = null
    private var localHttpClient: OkHttpClient? = null

    override val httpClient: OkHttpClient =
        (localHttpClient ?: createHttpClient())
            .apply { localHttpClient = this }

    override fun destroy() {
        localHttpClient = null
    }

    private fun createHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(defaultInterceptor)
            .callTimeout(timeOutConfig?.timeOutCall ?: TIME_OUT_CALL, TimeUnit.SECONDS)
            .connectTimeout(timeOutConfig?.timeOutConnect ?: TIME_OUT_CONNECT, TimeUnit.SECONDS)
            .writeTimeout(timeOutConfig?.timeOutWrite ?: TIME_OUT_WRITE, TimeUnit.SECONDS)
            .readTimeout(timeOutConfig?.timeOutRead ?: TIME_OUT_READ, TimeUnit.SECONDS)
            .hostnameVerifier { _, _ -> true }

        return builder.build()
    }

}