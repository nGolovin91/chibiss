package com.yoite.chibissapp.di.component

import android.app.Application
import com.yoite.chibissapp.ChibissApp
import com.yoite.chibissapp.di.delivery.GlobalDeliveryComponent
import com.yoite.chibissapp.di.module.AppBaseModule
import com.yoite.chibissapp.di.module.NetworkModule
import com.yoite.chibissapp.model.api.common.http.DefaultHttpClient
import com.yoite.chibissapp.model.api.common.services.ServiceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope


@AppScope
@Component(
    modules = [NetworkModule::class, AppBaseModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    // ===========================================================
    // Subcomponents
    // ===========================================================

    fun getGlobalGameComponent(): GlobalDeliveryComponent

    // ===========================================================
    // Dependencies
    // ===========================================================
    fun getDefaultHttpClient() : DefaultHttpClient

    fun getServicesFactory() : ServiceProvider

    // ===========================================================
    // Injects
    // ===========================================================
    fun inject(application: ChibissApp)
}