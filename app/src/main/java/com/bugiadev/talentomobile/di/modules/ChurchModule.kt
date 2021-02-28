package com.bugiadev.talentomobile.di.modules

import com.bugiadev.talentomobile.data.datasource.ChurchDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object ChurchModule {
    @JvmStatic
    @Provides
    fun providesChurchDataSource(@Named("commonRetrofit") retrofit: Retrofit): ChurchDataSource =
        retrofit.create(ChurchDataSource::class.java)
}