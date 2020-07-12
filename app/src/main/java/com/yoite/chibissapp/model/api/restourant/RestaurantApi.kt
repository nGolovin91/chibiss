package com.yoite.chibissapp.model.api.restourant

import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.model.api.restourant.schemes.RestaurantResponseScheme


interface RestaurantApi {
    suspend fun getRestaurantList(): ApiResult<List<RestaurantResponseScheme>>
}