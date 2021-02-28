package com.bugiadev.talentomobile

import android.app.Application
import com.bugiadev.talentomobile.di.ApplicationComponent
import com.bugiadev.talentomobile.di.ChurchComponent
import com.bugiadev.talentomobile.di.DaggerApplicationComponent
import com.bugiadev.talentomobile.di.DaggerChurchComponent

interface ApplicationComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}

class TalentoApp: Application(), ApplicationComponentProvider {
    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var churchComponent: ChurchComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(applicationContext)
        churchComponent = DaggerChurchComponent.factory().create(applicationComponent)
    }

    override fun provideApplicationComponent(): ApplicationComponent = applicationComponent
}