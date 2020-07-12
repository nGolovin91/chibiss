package com.yoite.chibissapp.model.api

import com.yoite.chibissapp.model.api.common.Either
import com.yoite.chibissapp.model.api.common.ErrorResult


typealias ApiResult<T> = Either<ErrorResult, T>