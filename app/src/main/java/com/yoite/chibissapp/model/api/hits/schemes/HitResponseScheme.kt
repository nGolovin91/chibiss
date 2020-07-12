package com.yoite.chibissapp.model.api.hits.schemes


data class HitResponseScheme(
    var ProductName: String?,
    var ProductImage: String?,
    var ProductPrice: Int?,
    var ProductDescription: String?,
    var RestaurantId: Long?,
    var RestaurantName: String?,
    var RestaurantLogo: String?
)