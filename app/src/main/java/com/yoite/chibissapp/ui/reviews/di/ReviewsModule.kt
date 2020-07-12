package com.yoite.chibissapp.ui.reviews.di

import androidx.lifecycle.ViewModel
import com.yoite.chibissapp.common.vm.ViewModelKey
import com.yoite.chibissapp.repository.reviews.ReviewRepositoryImpl
import com.yoite.chibissapp.repository.reviews.ReviewsRepository
import com.yoite.chibissapp.ui.reviews.ReviewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@ReviewScope
@Module
abstract class ReviewsModule {

    @ReviewScope
    @Binds
    abstract fun provideReviewsRepository(repository: ReviewRepositoryImpl): ReviewsRepository

    @ReviewScope
    @Binds
    @IntoMap
    @ViewModelKey(ReviewsViewModel::class)
    internal abstract fun provideReviewsViewModel(viewModel: ReviewsViewModel): ViewModel
}