package com.yoite.chibissapp.di.delivery

import com.yoite.chibissapp.model.api.ChibissTransportImpl
import com.yoite.chibissapp.model.api.hits.HitsApi
import com.yoite.chibissapp.model.api.restourant.RestaurantApi
import com.yoite.chibissapp.model.api.reviews.ReviewsApi
import dagger.Binds
import dagger.Module


@GlobalDeliveryScope
@Module
abstract class GlobalDeliveryModule {

    @GlobalDeliveryScope
    @Binds
    abstract fun provideRestaurantApi(transport: ChibissTransportImpl): RestaurantApi

    @GlobalDeliveryScope
    @Binds
    abstract fun provideReviewsApi(transport: ChibissTransportImpl): ReviewsApi

    @GlobalDeliveryScope
    @Binds
    abstract fun provideHitsApi(transport: ChibissTransportImpl): HitsApi

}