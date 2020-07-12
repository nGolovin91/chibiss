package com.yoite.chibissapp.model.api.common

import android.util.Log
import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.model.api.common.http.DefaultHttpClient
import com.yoite.chibissapp.model.api.common.parser.Parser
import com.yoite.chibissapp.model.api.common.services.ServiceProvider
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


open class BaseMainTransportImpl(
    private val serviceProvider: ServiceProvider,
    private val parser: Parser,
    private val retrofitBuilder: Retrofit.Builder,
    private val defaultHttpClientProvider: DefaultHttpClient
) {

    fun handleErrorResponse(response: Response<*>, listener: OnApiResultListener<*>) {
        listener.onFailure(response.toApiErrorResultModel())
    }

    fun Response<*>.toApiErrorResultModel() =
        ErrorResult.BadRequest(extractApiErrorFromBody(this))

    private fun extractApiErrorFromBody(response: Response<*>): RestApiError {
        val errorResponseBody = response.errorBody() ?: return RestApiError.Unknown()
        var apiErrorTypeByErrorCode: RestApiError = RestApiError.Unknown()
        var errorString: String? = null

        try {
            errorString = errorResponseBody.string()
            val asd = 0
//            val scheme = parser.parse(errorString, ErrorDataResponseScheme::class.java)
//
//            if (scheme.errorResponseSchemeList.isNotEmpty()) {
//
//                val responseScheme = scheme.errorResponseSchemeList.first()
//                val errorCode = responseScheme.code
//                val internalCode = responseScheme.internalCode
//                val message = responseScheme.message
//                apiErrorTypeByErrorCode = getApiErrorTypeByErrorCode(errorCode, message)
//                if (apiErrorTypeByErrorCode is RestApiError.Unknown) {
//                    Logger.logException(
//                            IllegalArgumentException(
//                                    "Received unknown error code.\n" +
//                                            "request: ${response.raw().request().url().encodedPath()}\n" +
//                                            "error_code: $errorCode\n" +
//                                            "internal_code: $internalCode"
//                            )
//                    )
//                }
//            }

        } catch (e: Exception) {
            val requestUrl = response.raw().request().url().encodedPath()
            Log.e("ChibbissApp", "",
                Throwable("url: $requestUrl\n\nresponse code - ${response.code()}\n\nerror:\n\n$errorString")
            )
        }

        return apiErrorTypeByErrorCode
    }

    fun handleFailureResponse(listener: OnApiResultListener<*>, throwable: Throwable) {
        listener.onFailure(ErrorResult.Unknown(throwable))
    }

    fun <T> handleFailureResponse(continuation: Continuation<ApiResult<T>>, throwable: Throwable) {
        continuation.resume(Either.Failure(ErrorResult.Unknown(throwable)))
    }

    protected fun <T> Call<T>.toListener(listener: OnApiResultListener<T>) =
        toListener(listener) { it }

    protected fun <T, R> Call<T>.toListener(
        listener: OnApiResultListener<R>,
        extract: (T) -> R
    ) {
        enqueue(object : Callback<T> {

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.isSuccessful) {
                    listener.onSuccess(extract(response.body()!!))
                } else {
                    handleErrorResponse(response, listener)
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) =
                handleFailureResponse(listener, throwable)
        })
    }

    protected fun Call<ResponseBody>.toEmptyListener(listener: OnApiResultListener<Boolean>) {
        enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    listener.onSuccess(true)

                } else {
                    handleErrorResponse(response, listener)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                handleFailureResponse(listener, throwable)
            }
        })
    }

    protected suspend fun Call<ResponseBody>.awaitEmpty(): ApiResult<Unit> =
        suspendCoroutine { continuation ->
            enqueue(object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        continuation.resume(Either.Success(Unit))
                    } else {
                        continuation.resume(Either.Failure(response.toApiErrorResultModel()))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                    handleFailureResponse(continuation, throwable)
                }
            })
        }

    protected suspend fun <T> Call<T>.await(): ApiResult<T> =
        suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        continuation.resume(Either.Success(response.body()!!))
                    } else {
                        continuation.resume(Either.Failure(response.toApiErrorResultModel()))
                    }
                }

                override fun onFailure(call: Call<T>, throwable: Throwable) {
                    handleFailureResponse(continuation, throwable)
                }
            })
        }

    // ===========================================================
    // Retrofit services
    // ===========================================================

    inline fun <reified TService> getRetrofitService(useCache: Boolean = true): TService =
        getRetrofitService(TService::class.java.canonicalName!!, TService::class.java, useCache)

    fun <TService> getRetrofitService(tag: String, serviceClass: Class<TService>, useCache: Boolean): TService {
        return if (useCache)
            serviceProvider.getService(tag, serviceClass) {
                val retrofit = retrofitBuilder
                    .client(defaultHttpClientProvider.getHttpClient())
                    .build()

                retrofit.create(serviceClass)
            }
        else {
            val retrofit = retrofitBuilder
                .client(defaultHttpClientProvider.getHttpClient())
                .build()

            retrofit.create(serviceClass)
        }
    }
}