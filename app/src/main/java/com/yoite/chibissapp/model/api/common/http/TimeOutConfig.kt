package com.yoite.chibissapp.model.api.common.http


class TimeOutConfig(
    val timeOutCall: Long,
    val timeOutConnect: Long,
    val timeOutWrite: Long,
    val timeOutRead: Long
)