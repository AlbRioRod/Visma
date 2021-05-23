package com.visma.technical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.visma.technical.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDashboardBinding>(
            this,
            R.layout.activity_dashboard
        )

        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)


        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->

            if (nd.id == R.id.currentFragment || nd.id == R.id.provincesFragment) {

                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }


        val appBarConfiguration = AppBarConfiguration(

            setOf(
                R.id.currentFragment, R.id.provincesFragment
            )
        )

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}