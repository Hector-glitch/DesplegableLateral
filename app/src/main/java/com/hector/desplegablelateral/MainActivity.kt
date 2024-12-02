package com.hector.desplegablelateral

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_view)
        bottomNav = findViewById(R.id.bottom_nav)

        // Configuración inicial: Cargar Fragment 1
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment1())
            .commit()

        // Listener para el menú lateral
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_fragment1 -> replaceFragment(Fragment1())
                R.id.nav_fragment2 -> replaceFragment(Fragment2())
                R.id.nav_fragment3 -> replaceFragment(Fragment3())
                R.id.nav_settings -> showToast("Settings clicked")
                R.id.nav_exit -> finish()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Listener para el BottomNavigationView
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_fragment1 -> replaceFragment(Fragment1())
                R.id.bottom_fragment2 -> replaceFragment(Fragment2())
                R.id.bottom_fragment3 -> replaceFragment(Fragment3())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
