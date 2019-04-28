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

import com.lydone.gazprombankspringhack.R
import com.lydone.gazprombankspringhack.RatingPositionAdapter

class PrivateRatingFragment : Fragment() {

    companion object {
        fun newInstance() = PrivateRatingFragment()
    }

    private lateinit var viewModel: PrivateRatingViewModel
    private lateinit var recyclerView: RecyclerView
    private val mAdapter = RatingPositionAdapter(null)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.private_rating_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.prRecycler)
        recyclerView.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PrivateRatingViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter
        viewModel.getPrivateRatingLiveData()
                .observe(this, Observer { rating ->
                    run {
                        mAdapter.setRatingPositions(rating)
                        mAdapter.notifyDataSetChanged()
                    }
                })
    }

}
