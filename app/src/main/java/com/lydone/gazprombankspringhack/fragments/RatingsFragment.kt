package com.lydone.gazprombankspringhack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lydone.gazprombankspringhack.MyFragmentPagerAdapter
import com.lydone.gazprombankspringhack.R

class RatingsFragment : Fragment() {

    companion object {
        fun newInstance() = RatingsFragment()
    }

    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ratings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewPager = view.findViewById(R.id.view_pager)
        mTabLayout = view.findViewById(R.id.tab_layout)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewPager.adapter = MyFragmentPagerAdapter(this.childFragmentManager, this.context!!)
        mTabLayout.setupWithViewPager(mViewPager)

    }

}
