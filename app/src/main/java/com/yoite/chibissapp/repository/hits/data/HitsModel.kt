package com.yoite.chibissapp.repository.hits.data


data class HitsModel(
    var ProductName: String,
    var ProductImage: String,
    var ProductPrice: Int,
    var ProductDescription: String,
    var RestaurantId: Long,
    var RestaurantName: String,
    var RestaurantLogo: String
)