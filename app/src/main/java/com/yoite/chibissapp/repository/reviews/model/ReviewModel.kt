package com.yoite.chibissapp.repository.reviews.model


data class ReviewModel(
    var IsPositive: Boolean,
    var Message: String,
    var DateAdded: Long,
    var UserFIO: String,
    var RestaurantName: String
)