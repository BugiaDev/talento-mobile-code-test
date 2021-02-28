package com.bugiadev.talentomobile.data.repository

import com.bugiadev.talentomobile.data.datasource.ChurchDataSource
import com.bugiadev.talentomobile.domain.models.ChurchModel
import com.bugiadev.talentomobile.domain.repository.ChurchRepository
import com.bugiadev.talentomobile.utils.mapNetworkErrors
import io.reactivex.Single
import javax.inject.Inject

class ChurchRepositoryImpl @Inject constructor(
        private val dataSource: ChurchDataSource
) : ChurchRepository {
    override fun getCatholicChurches(): Single<List<ChurchModel>> =
            dataSource.getCatholicChurches().mapNetworkErrors().map { catholicChurchEntity ->
                catholicChurchEntity.data.map {
                    it.toDomain()
                }
            }

    override fun getNonCatholicChurches(): Single<List<ChurchModel>> =
        dataSource.getNonCatholicChurches().mapNetworkErrors().map { nonCatholicChurchEntity ->
            nonCatholicChurchEntity.data.map {
                it.toDomain()
            }
        }
}