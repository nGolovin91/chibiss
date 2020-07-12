package com.yoite.chibissapp.model.api.restourant.schemes


data class RestaurantResponseScheme(
    var Name: String?,
    var Logo: String?,
    var MinCost: Int?,
    var DeliveryCost: Int?,
    var DeliveryTime: Int?,
    var PositiveReviews: Int?,
    var ReviewsCount: Int?,
    var Specializations: List<SpecializationsScheme>?
)