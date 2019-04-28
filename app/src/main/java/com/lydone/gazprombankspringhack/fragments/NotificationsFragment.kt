package com.lydone.gazprombankspringhack.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lydone.gazprombankspringhack.MainActivity
import com.lydone.gazprombankspringhack.NotificationsAdapter

import com.lydone.gazprombankspringhack.R

class NotificationsFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationsFragment()
    }

    private lateinit var viewModel: NotificationsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var startPollBtn: Button
    private val mAdapter = NotificationsAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notifications_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        startPollBtn = view.findViewById(R.id.startPollBtn)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter
        viewModel.getNotificationsLiveData()
            .observe(this, Observer { notifications ->
                run {
                    mAdapter.setNotifications(notifications)
                    mAdapter.notifyDataSetChanged()
                }
            })
        startPollBtn.setOnClickListener { v ->
            (activity as MainActivity).launchPollActivity()
        }
    }

}
