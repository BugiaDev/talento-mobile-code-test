package com.bugiadev.talentomobile.di.modules

import com.bugiadev.talentomobile.data.repository.ChurchRepositoryImpl
import com.bugiadev.talentomobile.di.annotations.ApplicationScope
import com.bugiadev.talentomobile.domain.repository.ChurchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @ApplicationScope
    @Binds
    abstract fun bindsChurchRepository(implementation: ChurchRepositoryImpl): ChurchRepository
}