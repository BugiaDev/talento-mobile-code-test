package com.bugiadev.talentomobile.domain.models

data class ChurchEntityModel(
    val data: List<ChurchModel>
)

data class ChurchModel(
    val id: String,
    val name: String,
    val description: String?,
    val isCatholic: Boolean,
    val address: AddressModel?
)

data class AddressModel(
        val locality: String?,
        val street: String?,
        val zipCode: String?
)