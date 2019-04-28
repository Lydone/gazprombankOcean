package com.lydone.gazprombankspringhack.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lydone.gazprombankspringhack.R

class TeamProgressFragment : Fragment() {

    companion object {
        fun newInstance() = TeamProgressFragment()
    }

    private lateinit var viewModel: TeamProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_progress_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamProgressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
