package com.yoite.chibissapp.model.api

import com.yoite.chibissapp.di.component.AppScope
import com.yoite.chibissapp.model.api.common.BaseMainTransportImpl
import com.yoite.chibissapp.model.api.common.http.DefaultHttpClient
import com.yoite.chibissapp.model.api.common.parser.Parser
import com.yoite.chibissapp.model.api.common.services.ServiceProvider
import com.yoite.chibissapp.model.api.hits.HitsServices
import com.yoite.chibissapp.model.api.restourant.RestaurantServices
import com.yoite.chibissapp.model.api.reviews.ReviewsServices
import retrofit2.Retrofit
import javax.inject.Inject


@AppScope
class ChibissTransportImpl @Inject constructor(
    serviceProvider: ServiceProvider,
    parser: Parser,
    retrofitBuilder: Retrofit.Builder,
    defaultHttpClientProvider: DefaultHttpClient
) : BaseMainTransportImpl(serviceProvider, parser, retrofitBuilder, defaultHttpClientProvider), ChibissTransport {

    // ===========================================================
    // RestaurantApi
    // ===========================================================

    override suspend fun getRestaurantList() =
        getRetrofitService<RestaurantServices>()
            .getRestaurantList()
            .await()

    // ===========================================================
    // ReviewsApi
    // ===========================================================

    override suspend fun getReviewList() =
        getRetrofitService<ReviewsServices>()
            .getReviewsList()
            .await()

    // ===========================================================
    // HitsApi
    // ===========================================================

    override suspend fun getHitsList() =
        getRetrofitService<HitsServices>()
            .getHitsList()
            .await()


}