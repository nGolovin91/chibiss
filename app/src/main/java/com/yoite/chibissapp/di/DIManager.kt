package com.yoite.chibissapp.di

import android.app.Application
import com.yoite.chibissapp.di.component.AppComponent
import com.yoite.chibissapp.di.component.DaggerAppComponent
import com.yoite.chibissapp.di.delivery.GlobalDeliveryComponent
import com.yoite.chibissapp.ui.hits.di.HitsComponent
import com.yoite.chibissapp.ui.restaurant.di.RestaurantComponent
import com.yoite.chibissapp.ui.reviews.di.ReviewComponent


class DIManager(private var application: Application) {

    // ===========================================================
    // AppComponent
    // ===========================================================
    var appComponent: AppComponent? = null
        get() {
            if (field == null) {
                field = DaggerAppComponent.builder()
                    .application(application)
                    .build()
            }

            return field
        }

    // ===========================================================
    // GlobalGameComponent
    // ===========================================================

    var globalDeliveryComponent: GlobalDeliveryComponent? = null
        get() {
            if (field == null) {
                field = appComponent?.getGlobalGameComponent()
            }

            return field
        }

    fun releaseGlobalInsuranceComponent() {
        releaseRestaurantComponent()
        releaseHitsComponent()
        releaseReviewsComponent()
        globalDeliveryComponent = null
    }

    // ===========================================================
    // RestaurantComponent
    // ===========================================================

    var restaurantComponent: RestaurantComponent? = null
        get() {
            if (field == null) {
                field = globalDeliveryComponent?.getRestaurantComponent()
            }

            return field
        }

    fun releaseRestaurantComponent() {
        restaurantComponent = null
    }

    // ===========================================================
    // HitsComponent
    // ===========================================================

    var hitsComponent: HitsComponent? = null
        get() {
            if (field == null) {
                field = globalDeliveryComponent?.getHitsComponent()
            }

            return field
        }

    fun releaseHitsComponent() {
        hitsComponent = null
    }

    // ===========================================================
    // ReviewComponent
    // ===========================================================

    var reviewsComponent: ReviewComponent? = null
        get() {
            if (field == null) {
                field = globalDeliveryComponent?.getReviewsComponent()
            }
            return field
        }

    fun releaseReviewsComponent() {
        reviewsComponent = null
    }
}