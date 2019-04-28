package com.lydone.gazprombankspringhack.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lydone.gazprombankspringhack.R
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var mViewModel: ProfileViewModel
    private lateinit var mNameTV: TextView
    private lateinit var mCoinsTV: TextView
    private lateinit var mProgressTV: TextView
    private lateinit var mPositionTV: TextView
    private lateinit var mTeamTV: TextView
    private lateinit var mCoinsPB: ProgressBar
    private lateinit var mTasksPB: ProgressBar
    private lateinit var mMultIV: ImageView
    private lateinit var mPointsPerDayTV: TextView
    private lateinit var mTeamPointsPerDayTV: TextView
    private lateinit var mProfileAvatarIV: ImageView
    private lateinit var mMedal0IV: ImageView
    private lateinit var mMedal1IV: ImageView
    private lateinit var mMedal2IV: ImageView
    private lateinit var mMedal3IV: ImageView
    private lateinit var mMedal4IV: ImageView
    private lateinit var mMedal5IV: ImageView
    private lateinit var mMedal6IV: ImageView
    private lateinit var mMedal7IV: ImageView
    private lateinit var mMedal8IV: ImageView
    private lateinit var mSprintChallengesTV: TextView
    private lateinit var mDailyChallengesTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mNameTV = view.findViewById(R.id.nameTV)
        mPositionTV = view.findViewById(R.id.positionTV)
        mTeamTV = view.findViewById(R.id.teamTV)
        mCoinsTV = view.findViewById(R.id.coinsTV)
        mProgressTV = view.findViewById(R.id.progressTV)
        mMultIV = view.findViewById(R.id.multIV)
        mCoinsPB = view.findViewById(R.id.coinsPB)
        mTasksPB = view.findViewById(R.id.tasksPB)
        mPointsPerDayTV = view.findViewById(R.id.pointsPerDayTV)
        mTeamPointsPerDayTV = view.findViewById(R.id.teamPointsPerDayTV)
        mProfileAvatarIV = view.findViewById(R.id.profileAvatarIV)
        mMedal0IV = view.findViewById(R.id.medal0IV)
        mMedal1IV = view.findViewById(R.id.medal1IV)
        mMedal2IV = view.findViewById(R.id.medal2IV)
        mMedal3IV = view.findViewById(R.id.medal3IV)
        mMedal4IV = view.findViewById(R.id.medal4IV)
        mMedal5IV = view.findViewById(R.id.medal5IV)
        mMedal6IV = view.findViewById(R.id.medal6IV)
        mMedal7IV = view.findViewById(R.id.medal7IV)
        mMedal8IV = view.findViewById(R.id.medal8IV)
        mDailyChallengesTV = view.findViewById(R.id.dailyChallengesTV)
        mSprintChallengesTV = view.findViewById(R.id.sprintChallengesTV)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        mViewModel.getProfileInfoLiveData().observe(this,
            Observer { profileInfo ->
                run {
                    mNameTV.text = profileInfo.name
                    mPositionTV.text = profileInfo.position
                    mTeamTV.text = profileInfo.team
                    mCoinsPB.progress =
                        (profileInfo.pointsPerDay.toDouble() / profileInfo.pointsAverage * 100).toInt()
                    mTasksPB.progress =
                        (profileInfo.tasksPerDay.toDouble() / profileInfo.tasksAverage * 100).toInt()
                    mProgressTV.text = "сделано: " + mTasksPB.progress + "%"
                    mCoinsTV.text = "заработано: " + profileInfo.pointsPerDay + "/" + profileInfo.pointsAverage
                    mPointsPerDayTV.text = profileInfo.pointsPerDay.toString()
                    mTeamPointsPerDayTV.text = profileInfo.teamPointsPerDay.toString()
                }
            })
        Picasso.get().load("http://10.20.2.219:8000/actum/images/6.jpg")./*resize(150, 150)
            .centerCrop().*/into(mProfileAvatarIV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/1.png").into(mMedal0IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/2.png").into(mMedal1IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/3.png").into(mMedal2IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/4.png").into(mMedal3IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/5.png").into(mMedal4IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/6.png").into(mMedal5IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/7.png").into(mMedal6IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/8.png").into(mMedal7IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/awards/9.png").into(mMedal8IV)
        Picasso.get().load("http://10.20.2.219:8000/actum/multiplicators/x5.png").into(mMultIV)

        mViewModel.getSprintChallenges().observe(this,
            Observer { sprintChallenges ->
                run {
                    if (sprintChallenges.isNotEmpty())
                        mSprintChallengesTV.text = sprintChallenges[0].description
                }
            })

        mViewModel.getDailyChallenges().observe(this,
            Observer { dailyChallenges ->
                run {
                    if (dailyChallenges.isNotEmpty())
                        mDailyChallengesTV.text = dailyChallenges[0].description
                }
            })
    }

}
