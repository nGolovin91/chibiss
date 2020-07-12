package com.yoite.chibissapp.model.api.hits

import com.yoite.chibissapp.Constants
import com.yoite.chibissapp.model.api.hits.schemes.HitResponseScheme
import retrofit2.Call
import retrofit2.http.GET


interface HitsServices {

    @GET(Constants.API_HITS_LIST)
    fun getHitsList(): Call<List<HitResponseScheme>>
}