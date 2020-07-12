package com.yoite.chibissapp.di.delivery

import com.yoite.chibissapp.ui.hits.di.HitsComponent
import com.yoite.chibissapp.ui.restaurant.di.RestaurantComponent
import com.yoite.chibissapp.ui.reviews.di.ReviewComponent
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GlobalDeliveryScope


@GlobalDeliveryScope
@Subcomponent(
    modules = [GlobalDeliveryModule::class]
)
interface GlobalDeliveryComponent {

    // ===========================================================
    // Subcomponents
    // ===========================================================

    fun getRestaurantComponent(): RestaurantComponent

    fun getHitsComponent(): HitsComponent

    fun getReviewsComponent(): ReviewComponent

}