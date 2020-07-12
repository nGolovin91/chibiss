package com.yoite.chibissapp.model.api.reviews

import com.yoite.chibissapp.Constants
import com.yoite.chibissapp.model.api.reviews.schemes.ReviewResponseScheme
import retrofit2.Call
import retrofit2.http.GET


interface ReviewsServices {

    @GET(Constants.API_REVIEWS_LIST)
    fun getReviewsList(): Call<List<ReviewResponseScheme>>
}