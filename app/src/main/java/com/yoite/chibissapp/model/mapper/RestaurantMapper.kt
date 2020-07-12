package com.yoite.chibissapp.model.mapper

import com.yoite.chibissapp.model.api.restourant.schemes.RestaurantResponseScheme
import com.yoite.chibissapp.repository.restaurant.data.RestaurantModel


fun RestaurantResponseScheme.mapToRestaurantModel() =
    RestaurantModel(
        Name ?: "",
        Logo ?: "",
        MinCost ?: 0,
        DeliveryCost ?: 0,
        DeliveryTime ?: 0,
        PositiveReviews ?: 0,
        ReviewsCount ?: 0,
        Specializations?.map { it.Name ?: "" } ?: emptyList()
    )