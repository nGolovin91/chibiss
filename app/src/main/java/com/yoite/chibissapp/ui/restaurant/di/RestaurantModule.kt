package com.yoite.chibissapp.ui.restaurant.di

import androidx.lifecycle.ViewModel
import com.yoite.chibissapp.common.vm.ViewModelKey
import com.yoite.chibissapp.repository.restaurant.RestaurantRepository
import com.yoite.chibissapp.repository.restaurant.RestaurantRepositoryImpl
import com.yoite.chibissapp.ui.restaurant.RestaurantViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@RestaurantScope
@Module
abstract class RestaurantModule {

    @RestaurantScope
    @Binds
    abstract fun provideRestaurantRepository(repository: RestaurantRepositoryImpl): RestaurantRepository

    @RestaurantScope
    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel::class)
    internal abstract fun provideRestaurantViewModel(viewModel: RestaurantViewModel): ViewModel
}