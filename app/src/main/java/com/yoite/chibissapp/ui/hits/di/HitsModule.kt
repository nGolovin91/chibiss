package com.yoite.chibissapp.ui.hits.di

import androidx.lifecycle.ViewModel
import com.yoite.chibissapp.common.vm.ViewModelKey
import com.yoite.chibissapp.repository.hits.HitsRepository
import com.yoite.chibissapp.repository.hits.HitsRepositoryImpl
import com.yoite.chibissapp.ui.hits.HitsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@HitsScope
@Module
abstract class HitsModule {

    @HitsScope
    @Binds
    abstract fun provideHitsRepository(repository: HitsRepositoryImpl): HitsRepository

    @HitsScope
    @Binds
    @IntoMap
    @ViewModelKey(HitsViewModel::class)
    abstract fun provideHitsViewModel(viewModel: HitsViewModel): ViewModel
}