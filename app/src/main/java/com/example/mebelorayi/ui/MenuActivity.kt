package com.example.mebelorayi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mebelorayi.Fragments.HomeFragment
import com.example.mebelorayi.Fragments.OrderFragment
import com.example.mebelorayi.Fragments.ProfileFragment
import com.example.mebelorayi.R
import kotlinx.android.synthetic.main.main_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        supportActionBar?.hide()

        val homeFragment = HomeFragment()
        val orderFragment = OrderFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment)

        BottomNavMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.order -> makeCurrentFragment(orderFragment)
                R.id.profile -> makeCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_wallpaper, fragment)
                .commit()
        }
    }


}