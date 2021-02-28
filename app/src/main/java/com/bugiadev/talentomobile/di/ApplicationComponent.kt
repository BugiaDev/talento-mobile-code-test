package com.bugiadev.talentomobile.di

import android.content.Context
import com.bugiadev.talentomobile.di.annotations.ApplicationScope
import com.bugiadev.talentomobile.di.modules.ChurchModule
import com.bugiadev.talentomobile.di.modules.NetworkModule
import com.bugiadev.talentomobile.di.modules.RepositoryModule
import com.bugiadev.talentomobile.domain.repository.ChurchRepository
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@ApplicationScope
@Component(modules = [NetworkModule::class, RepositoryModule::class, ChurchModule::class])
interface ApplicationComponent {
    val okHttpClient: OkHttpClient
    val converter: Converter.Factory
    val adapter: CallAdapter.Factory
    val context: Context
    val churchRepository: ChurchRepository

    @Named("commonRetrofit")
    fun commonRetrofit(): Retrofit

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ): ApplicationComponent
    }
}