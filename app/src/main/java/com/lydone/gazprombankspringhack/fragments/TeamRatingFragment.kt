package com.lydone.gazprombankspringhack.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lydone.gazprombankspringhack.NotificationsAdapter

import com.lydone.gazprombankspringhack.R
import com.lydone.gazprombankspringhack.RatingPositionAdapter

class TeamRatingFragment : Fragment() {

    companion object {
        fun newInstance() = TeamRatingFragment()
    }

    private lateinit var viewModel: TeamRatingViewModel
    private lateinit var recyclerView: RecyclerView
    private val mAdapter = RatingPositionAdapter(null)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_rating_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.trRecycler)
        recyclerView.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamRatingViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter
        viewModel.getTeamRatingLiveData()
                .observe(this, Observer { rating ->
                    run {
                        mAdapter.setRatingPositions(rating)
                        mAdapter.notifyDataSetChanged()
                    }
                })
    }

}
