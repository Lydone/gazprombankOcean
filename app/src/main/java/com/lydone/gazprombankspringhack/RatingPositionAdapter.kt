package com.lydone.gazprombankspringhack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lydone.gazprombankspringhack.data.RatingPositionData

class RatingPositionAdapter(private var mRatingPositions: List<RatingPositionData>?) :
        RecyclerView.Adapter<RatingPositionAdapter.RatingPositionVH>() {

    fun setRatingPositions(ratingPositions: List<RatingPositionData>) {
        mRatingPositions = ratingPositions
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingPositionVH {
        val v =
                LayoutInflater.from(parent.context).inflate(R.layout.item_rating_position, parent, false)
        return RatingPositionVH(v)
    }

    override fun onBindViewHolder(holder: RatingPositionVH, position: Int) {
        holder.bind(mRatingPositions, position)
    }

    override fun getItemCount(): Int {
        if (mRatingPositions == null)
            return 0
        return mRatingPositions!!.size
    }

    class RatingPositionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mRatingPositionName: TextView = itemView.findViewById(R.id.ratingPositionNameTV)
        private val mRatingPositionNumber: TextView = itemView.findViewById(R.id.ratingPositionNumberTV)
        private val mRatingPositionCoins: TextView = itemView.findViewById(R.id.raitingPositionMoneyTV)

        internal fun bind(positions: List<RatingPositionData>?, position: Int) {
            mRatingPositionName.text = positions!![position].name
            mRatingPositionNumber.text = positions[position].rating.toString()
            mRatingPositionCoins.text = positions[position].points.toString()
        }
    }
}
