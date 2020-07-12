package com.yoite.chibissapp.repository.hits

import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.repository.hits.data.HitsModel


interface HitsRepository {
    suspend fun getHitsList(): ApiResult<List<HitsModel>>
}