package com.bugiadev.talentomobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bugiadev.talentomobile.databinding.ActivityMainBinding
import com.bugiadev.talentomobile.di.ChurchComponent
import com.bugiadev.talentomobile.di.ChurchInjection
import com.bugiadev.talentomobile.di.DaggerChurchComponent
import com.bugiadev.talentomobile.ui.navigation.Navigator
import com.bugiadev.talentomobile.utils.injector
import com.bugiadev.talentomobile.utils.setupWithNavController
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity(), ChurchInjection {
    private var _binding: ActivityMainBinding? = null
    private var binding: ActivityMainBinding
        get() = _binding
                ?: throw IllegalStateException("Binding is null, check the status of your Activity ${this::class}")
        set(value) {
            _binding = value
        }

    private lateinit var churchComponent: ChurchComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        )

        churchComponent = DaggerChurchComponent.factory().create(injector)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onDestroy() {
        Navigator.currentNavController = null
        binding.coordinator.setOnHierarchyChangeListener(null)
        _binding = null
        super.onDestroy()
    }

    private fun setupBottomNavigationBar() {
        binding.let { _binding ->
            val bottomBar = _binding.bottomNavigationBar.bottomNavigationView
            val controller = bottomBar.setupWithNavController(
                    navGraphIds = Navigator.getBottomBarGraphList(),
                    fragmentManager = supportFragmentManager,
                    containerId = R.id.nav_host_fragment,
                    onDestinationChangedListener = { navController, navDestination ->
                        Log.d("navigation", "navDestination: ${navDestination.label}")
                    }
            )

            Navigator.currentNavController = controller
        }
    }

    override fun getChurchComponent(): ChurchComponent = churchComponent
}