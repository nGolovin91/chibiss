package com.yoite.chibissapp.repository.restaurant.data


data class RestaurantModel(
    var Name: String,
    var Logo: String,
    var MinCost: Int,
    var DeliveryCost: Int,
    var DeliveryTime: Int,
    var PositiveReviews: Int,
    var ReviewsCount: Int,
    var specializations: List<String>
)