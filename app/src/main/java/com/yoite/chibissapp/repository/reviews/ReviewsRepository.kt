package com.yoite.chibissapp.repository.reviews

import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.repository.reviews.model.ReviewModel


interface ReviewsRepository {
    suspend fun getReviewsList(): ApiResult<List<ReviewModel>>
}