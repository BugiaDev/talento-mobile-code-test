package com.bugiadev.talentomobile.data.datasource

import com.bugiadev.talentomobile.data.entities.CatholicChurchEntity
import com.bugiadev.talentomobile.data.entities.NonCatholicChurchEntity
import io.reactivex.Single
import retrofit2.http.GET

interface ChurchDataSource {
    @GET("catalogo/209426-0-templos-catolicas.json")
    fun getCatholicChurches(): Single<CatholicChurchEntity>

    @GET("catalogo/209434-0-templos-otros.json")
    fun getNonCatholicChurches(): Single<NonCatholicChurchEntity>
}