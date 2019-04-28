package com.lydone.gazprombankspringhack

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lydone.gazprombankspringhack.fragments.PrivateRatingFragment
import com.lydone.gazprombankspringhack.fragments.TeamRatingFragment

class MyFragmentPagerAdapter(fm: FragmentManager, val context: Context) : FragmentPagerAdapter(fm) {
    private val titles = arrayListOf("Личный рейтинг", "Командный рейтинг")
    private val privateRatingFragment = PrivateRatingFragment.newInstance()
    private val teamRatingFragment = TeamRatingFragment.newInstance()

    override fun getItem(position: Int): Fragment {
        return if (position == 0)
            privateRatingFragment
        else
            teamRatingFragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}