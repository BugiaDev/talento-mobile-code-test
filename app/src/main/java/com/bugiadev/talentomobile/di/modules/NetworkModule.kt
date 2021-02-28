package com.bugiadev.talentomobile.di.modules

import com.bugiadev.talentomobile.BuildConfig
import com.bugiadev.talentomobile.data.network.TalentoRxCallAdapterFactory
import com.bugiadev.talentomobile.di.annotations.ApplicationScope
import com.bugiadev.talentomobile.utils.delegatingCallFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
object NetworkModule {
    @JvmStatic
    @Provides
    @Named("baseCommonUrl")
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(30L, TimeUnit.SECONDS)
        }.build()
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesCallAdapterFactory(): CallAdapter.Factory {
        //return RxJava2CallAdapterFactory.create()
        return TalentoRxCallAdapterFactory.create()
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    @Named("commonRetrofit")
    fun providesRetrofit(
        @Named("baseCommonUrl") baseUrl: String,
        httpClient: dagger.Lazy<OkHttpClient>,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .delegatingCallFactory(httpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }
}