package com.yoite.chibissapp.model.api.hits

import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.model.api.hits.schemes.HitResponseScheme


interface HitsApi {
    suspend fun getHitsList(): ApiResult<List<HitResponseScheme>>
}