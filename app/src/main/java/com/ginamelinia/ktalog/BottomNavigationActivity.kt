package com.ginamelinia.ktalog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ginamelinia.ktalog.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        navController = navHomeFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        setupNavigationListener()
    }

    private fun setupNavigationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility = when (destination.id) {
                R.id.genreFragment,
                R.id.dramaDetailFragment,
                R.id.dramaFragment,
                -> View.GONE
                else -> View.VISIBLE
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
