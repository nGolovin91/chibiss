package com.yoite.chibissapp.model.api.common


sealed class RestApiError(open val message: String) {
    class Unknown(override val message: String = "") : RestApiError(message)
}