package com.yoite.chibissapp.repository.restaurant

import com.yoite.chibissapp.model.api.common.map
import com.yoite.chibissapp.model.api.restourant.RestaurantApi
import com.yoite.chibissapp.model.mapper.mapToRestaurantModel
import com.yoite.chibissapp.ui.restaurant.di.RestaurantScope
import javax.inject.Inject


@RestaurantScope
class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApi
) : RestaurantRepository {

    override suspend fun getRestaurantList() =
        restaurantApi.getRestaurantList().map { list -> list.map { it.mapToRestaurantModel() } }

}