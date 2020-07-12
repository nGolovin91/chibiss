package com.yoite.chibissapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.yoite.chibissapp.common.vm.ViewModelFactory
import com.yoite.chibissapp.di.component.AppScope
import dagger.Binds
import dagger.Module


@AppScope
@Module
abstract class AppBaseModule {

    @AppScope
    @Binds
    abstract fun provideContext(application: Application): Context

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}