package com.yoite.chibissapp.ui.reviews.di

import com.yoite.chibissapp.repository.reviews.ReviewsRepository
import com.yoite.chibissapp.ui.reviews.ReviewsFragment
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ReviewScope

@ReviewScope
@Subcomponent(
    modules = [ReviewsModule::class]
)
interface ReviewComponent {

    fun getReviewsRepository(): ReviewsRepository

    fun inject(fragment: ReviewsFragment)
}