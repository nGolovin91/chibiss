package com.yoite.chibissapp.model.api.reviews.schemes


data class ReviewResponseScheme(
    var IsPositive: Boolean?,
    var Message: String?,
    var DateAdded: String?,
    var UserFIO: String?,
    var RestaurantName: String?
)