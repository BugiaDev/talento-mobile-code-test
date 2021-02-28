package com.bugiadev.talentomobile.data.entities

import com.bugiadev.talentomobile.domain.DomainMappable
import com.bugiadev.talentomobile.domain.models.AddressModel
import com.bugiadev.talentomobile.domain.models.ChurchEntityModel
import com.bugiadev.talentomobile.domain.models.ChurchModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatholicChurchEntity (
    @Json(name = "@graph") val data: List<CatholicChurch>
) : DomainMappable<ChurchEntityModel> {
    override fun toDomain(): ChurchEntityModel =
        ChurchEntityModel(
            data = data.map { it.toDomain() }
        )
}

@JsonClass(generateAdapter = true)
data class NonCatholicChurchEntity (
    @Json(name = "@graph") val data: List<NonCatholicChurch>
) : DomainMappable<ChurchEntityModel> {
    override fun toDomain(): ChurchEntityModel =
        ChurchEntityModel(
            data = data.map { it.toDomain() }
        )
}

@JsonClass(generateAdapter = true)
data class CatholicChurch (
    @Json(name = "@id") val id: String,
    @Json(name = "title") val name: String,
    @Json(name = "description") val description: String? = "",
    @Json(name = "address") val address: Address? = Address()
) : DomainMappable<ChurchModel> {
    override fun toDomain(): ChurchModel =
            ChurchModel(
                    id = id,
                    name = name,
                    description = description,
                    isCatholic = true,
                    address = address?.toDomain()
            )
}

@JsonClass(generateAdapter = true)
data class NonCatholicChurch (
        @Json(name = "@id") val id: String,
        @Json(name = "title") val name: String,
        @Json(name = "description") val description: String? = "",
        @Json(name = "address") val address: Address? = Address()
) : DomainMappable<ChurchModel> {
    override fun toDomain(): ChurchModel =
            ChurchModel(
                    id = id,
                    name = name,
                    description = description,
                    isCatholic = false,
                    address = address?.toDomain()
            )
}

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "locality") val locality: String? = "",
    @Json(name = "street-address") val street: String? = "",
    @Json(name = "postal-code") val zipCode: String? = "",
) : DomainMappable<AddressModel> {
    override fun toDomain(): AddressModel =
            AddressModel(
                    locality = locality,
                    street = street,
                    zipCode = zipCode
            )
}