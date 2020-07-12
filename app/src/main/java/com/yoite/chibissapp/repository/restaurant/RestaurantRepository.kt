package com.yoite.chibissapp.repository.restaurant

import com.yoite.chibissapp.model.api.ApiResult
import com.yoite.chibissapp.repository.restaurant.data.RestaurantModel


interface RestaurantRepository {
    suspend fun getRestaurantList(): ApiResult<List<RestaurantModel>>
}