package com.bugiadev.talentomobile.di

import com.bugiadev.talentomobile.di.annotations.ActivityScope
import com.bugiadev.talentomobile.di.annotations.ApplicationScope
import com.bugiadev.talentomobile.di.modules.NetworkModule
import com.bugiadev.talentomobile.di.modules.RepositoryModule
import com.bugiadev.talentomobile.ui.main.ChurchListFragment
import com.bugiadev.talentomobile.ui.main.ChurchViewModel
import com.bugiadev.talentomobile.ui.main.TempleListFragment
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class]
)
interface ChurchComponent {
    val churchViewModel: ChurchViewModel

    fun inject(fragment: ChurchListFragment)
    fun inject(fragment: TempleListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            applicationComponent: ApplicationComponent
        ): ChurchComponent
    }
}

interface ChurchInjection {
    fun getChurchComponent(): ChurchComponent
}