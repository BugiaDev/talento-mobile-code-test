package com.bugiadev.talentomobile.domain.repository

import com.bugiadev.talentomobile.domain.models.ChurchModel
import io.reactivex.Single

interface ChurchRepository {
    fun getCatholicChurches(): Single<List<ChurchModel>>
    fun getNonCatholicChurches(): Single<List<ChurchModel>>
}