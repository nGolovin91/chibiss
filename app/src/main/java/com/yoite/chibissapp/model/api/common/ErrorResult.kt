package com.yoite.chibissapp.model.api.common


sealed class ErrorResult {
    data class BadRequest(val restApiError: RestApiError = RestApiError.Unknown()) : ErrorResult()
    data class NeedNetwork(val restApiError: RestApiError = RestApiError.Unknown()) : ErrorResult()
    data class Unknown(val error: Throwable) : ErrorResult()
}

val ErrorResult.restApiError: RestApiError?
    get() = when (this) {
        is ErrorResult.BadRequest -> restApiError
        is ErrorResult.NeedNetwork -> restApiError
        else -> null
    }