package com.yoite.chibissapp.ui.restaurant.di

import com.yoite.chibissapp.repository.restaurant.RestaurantRepository
import com.yoite.chibissapp.ui.restaurant.RestaurantFragment
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RestaurantScope

@RestaurantScope
@Subcomponent(
    modules = [RestaurantModule::class]
)
interface RestaurantComponent {

    fun getRestaurantRepository(): RestaurantRepository

    fun inject(fragment: RestaurantFragment)
}