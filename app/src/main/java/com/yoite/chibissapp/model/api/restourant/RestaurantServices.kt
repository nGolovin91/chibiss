package com.yoite.chibissapp.model.api.restourant

import com.yoite.chibissapp.Constants
import com.yoite.chibissapp.model.api.restourant.schemes.RestaurantResponseScheme
import retrofit2.Call
import retrofit2.http.GET


interface RestaurantServices {

    @GET(Constants.API_RESTAURANT_LIST)
    fun getRestaurantList(): Call<List<RestaurantResponseScheme>>
}