package com.yoite.chibissapp.model.api.reviews

import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.model.api.reviews.schemes.ReviewResponseScheme


interface ReviewsApi {
    suspend fun getReviewList(): ApiResult<List<ReviewResponseScheme>>
}