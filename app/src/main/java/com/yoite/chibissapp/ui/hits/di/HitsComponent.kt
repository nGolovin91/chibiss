package com.yoite.chibissapp.ui.hits.di

import com.yoite.chibissapp.repository.hits.HitsRepository
import com.yoite.chibissapp.ui.hits.HitsFragment
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HitsScope

@HitsScope
@Subcomponent(
    modules = [HitsModule::class]
)
interface HitsComponent {
    fun getHitsRepository() : HitsRepository
    fun inject(fragment: HitsFragment)
}