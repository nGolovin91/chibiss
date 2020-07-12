package com.yoite.chibissapp.model.api.common


interface OnApiResultListener<T> {
    fun onSuccess(result: T)
    fun onFailure(errorResult: ErrorResult)
}