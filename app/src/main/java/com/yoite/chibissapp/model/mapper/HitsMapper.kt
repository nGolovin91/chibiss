package com.yoite.chibissapp.model.mapper

import com.yoite.chibissapp.model.api.hits.schemes.HitResponseScheme
import com.yoite.chibissapp.repository.hits.data.HitsModel


fun HitResponseScheme.mapToHitModel() =
    HitsModel(
        ProductName ?: "",
        ProductImage ?: "",
        ProductPrice ?: 0,
        ProductDescription ?: "",
        RestaurantId ?: 0,
        RestaurantName ?: "",
        RestaurantLogo ?: ""
    )