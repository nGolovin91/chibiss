package com.yoite.chibissapp.model.mapper

import com.yoite.chibissapp.common.convertStringDateToLong
import com.yoite.chibissapp.model.api.reviews.schemes.ReviewResponseScheme
import com.yoite.chibissapp.repository.reviews.model.ReviewModel


fun ReviewResponseScheme.mapToReviewModel() =
    ReviewModel(
        IsPositive ?: false,
        Message ?: "",
        if(DateAdded.isNullOrEmpty()) 0 else convertStringDateToLong(DateAdded!!),
        UserFIO ?: "",
        RestaurantName ?: ""
    )