package com.bugiadev.talentomobile.ui.presentation

import android.os.Parcelable
import androidx.annotation.Keep
import com.bugiadev.talentomobile.domain.models.ChurchModel
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ChurchDisplay (
        val id: String,
        val name: String,
        val description: String? = null,
        val isCatholic: Boolean,
        val address: String? = null,
        val zipCode: String? = null,
        val locality: String? = null
) : Parcelable

fun ChurchModel.toDisplay() : ChurchDisplay =
    ChurchDisplay(
            id = id,
            name = name,
            description = description?: "",
            isCatholic = isCatholic,
            address = address?.street?: "",
            zipCode = address?.zipCode?: "",
            locality = address?.locality?: ""
    )