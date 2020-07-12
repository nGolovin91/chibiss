package com.yoite.chibissapp.repository.reviews

import com.yoite.chibissapp.model.api.common.map
import com.yoite.chibissapp.model.api.reviews.ReviewsApi
import com.yoite.chibissapp.model.mapper.mapToReviewModel
import com.yoite.chibissapp.ui.reviews.di.ReviewScope
import javax.inject.Inject


@ReviewScope
class ReviewRepositoryImpl @Inject constructor(
    private val reviewsApi: ReviewsApi
) : ReviewsRepository {

    override suspend fun getReviewsList() =
        reviewsApi.getReviewList().map { list -> list.map { it.mapToReviewModel() } }

}