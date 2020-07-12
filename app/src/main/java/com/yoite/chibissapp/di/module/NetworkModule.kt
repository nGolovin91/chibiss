package com.yoite.chibissapp.di.module

import com.google.gson.Gson
import com.yoite.chibissapp.Constants
import com.yoite.chibissapp.di.component.AppScope
import com.yoite.chibissapp.model.api.common.http.DefaultHttpClient
import com.yoite.chibissapp.model.api.common.http.DefaultInterceptor
import com.yoite.chibissapp.model.api.common.parser.ChibissParser
import com.yoite.chibissapp.model.api.common.parser.Parser
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@AppScope
@Module
abstract class NetworkModule {

    @AppScope
    @Module
    companion object {

        @AppScope
        @Provides
        @JvmStatic
        fun provideGson(parser: Parser): Gson {
            return parser.defaultParser
        }

        @AppScope
        @Provides
        @JvmStatic
        fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
        }

        // ===========================================================
        // Interceptors
        // ===========================================================

        @AppScope
        @Provides
        @JvmStatic
        fun provideDefaultHttpClient(
            @Named(DefaultInterceptor.QUALIFIER) interceptor: Interceptor
        ): DefaultHttpClient {
            return DefaultHttpClient(interceptor)
        }
    }

    // ===========================================================
    // Parsers
    // ===========================================================

    @AppScope
    @Binds
    abstract fun provideParser(parser: ChibissParser): Parser

    // ===========================================================
    // Interceptors
    // ===========================================================

    @AppScope
    @Binds
    @Named(DefaultInterceptor.QUALIFIER)
    abstract fun provideDefaultInterceptor(interceptor: DefaultInterceptor): Interceptor

}