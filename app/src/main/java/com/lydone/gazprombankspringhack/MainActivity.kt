package com.lydone.gazprombankspringhack

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lydone.gazprombankspringhack.fragments.ProfileViewModel

class MainActivity : AppCompatActivity() {
    private val navOptions = NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_pop_exit_anim).build()

    private lateinit var mNavController: NavController
    private var mCurrentFragmentId: Int = 0
    private lateinit var coinsTV: TextView
    private lateinit var nameTV: TextView
    private lateinit var mViewModel: ProfileViewModel
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    if (mCurrentFragmentId != R.id.profileFragment) {
                        mCurrentFragmentId = R.id.profileFragment
                        mNavController.navigate(R.id.profileFragment, null, navOptions)
                        return@OnNavigationItemSelectedListener true
                    }
                }
                R.id.navigation_ratings -> {
                    if (mCurrentFragmentId != R.id.ratingsFragment) {
                        mCurrentFragmentId = R.id.ratingsFragment
                        mNavController.navigate(R.id.ratingsFragment, null, navOptions)
                        return@OnNavigationItemSelectedListener true
                    }
                }

                R.id.navigation_notifications -> {
                    if (mCurrentFragmentId != R.id.notificationsFragment) {
                        mCurrentFragmentId = R.id.notificationsFragment
                        mNavController.navigate(R.id.notificationsFragment, null, navOptions)
                        return@OnNavigationItemSelectedListener true
                    }
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        coinsTV = findViewById(R.id.toolbarCoinsTV)
        nameTV = findViewById(R.id.toolbarNameTV)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        mViewModel.getProfileInfoLiveData().observe(this, Observer { profileInfo ->
            run {
                coinsTV.text = profileInfo.pointsPerDay.toString()
                nameTV.text = profileInfo.name
            }
        }
        )
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    fun launchPollActivity() {
        mNavController.navigate(R.id.pollActivity)
    }
}
