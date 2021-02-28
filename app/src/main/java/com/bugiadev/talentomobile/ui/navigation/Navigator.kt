package com.bugiadev.talentomobile.ui.navigation

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.bugiadev.talentomobile.R

object Navigator {
    var currentNavController: LiveData<NavController>? = null

    fun getBottomBarGraphList(): List<Int> {
        val catholic = R.navigation.catholic_nav
        val nonCatholic = R.navigation.non_catholic_nav
        return listOf(catholic, nonCatholic)
    }
}