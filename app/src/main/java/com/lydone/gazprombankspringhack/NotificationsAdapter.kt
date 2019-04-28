package com.lydone.gazprombankspringhack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lydone.gazprombankspringhack.data.NotificationData

class NotificationsAdapter(private var mNotifications: List<NotificationData>?) :
    RecyclerView.Adapter<NotificationsAdapter.NotificationVH>() {

    fun setNotifications(notifications: List<NotificationData>) {
        mNotifications = notifications
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationVH {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationVH(v)
    }

    override fun onBindViewHolder(holder: NotificationVH, position: Int) {
        holder.bind(mNotifications, position)
    }

    override fun getItemCount(): Int {
        if (mNotifications == null)
            return 0
        return mNotifications!!.size
    }

    class NotificationVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mDescriptionTV: TextView = itemView.findViewById(R.id.descriptionIV)
        private val mLinLayout: ConstraintLayout = itemView.findViewById(R.id.lin)

        internal fun bind(notifications: List<NotificationData>?, position: Int) {
            mDescriptionTV.text = notifications!![position].description
            if (notifications[position].type.contains("бой")) {
                mLinLayout.background = ContextCompat.getDrawable(mDescriptionTV.context, R.drawable.battle)
                mDescriptionTV.setTextColor(
                    ContextCompat.getColor(
                        mDescriptionTV.context,
                        R.color.colorWhiteMainBackground
                    )
                )
            } else if (notifications[position].type.contains("письмо")) {
                mLinLayout.background = ContextCompat.getDrawable(mDescriptionTV.context, R.drawable.letter)
                mDescriptionTV.setTextColor(
                    ContextCompat.getColor(
                        mDescriptionTV.context,
                        R.color.black
                    )
                )
            } else if (notifications[position].type.contains("грамота")) {
                mLinLayout.background = ContextCompat.getDrawable(mDescriptionTV.context, R.drawable.gramota)
                mDescriptionTV.setTextColor(
                    ContextCompat.getColor(
                        mDescriptionTV.context,
                        R.color.colorWhiteMainBackground
                    )
                )
            }
            else {
                mLinLayout.background = ContextCompat.getDrawable(mDescriptionTV.context, R.drawable.itable)
                mDescriptionTV.setTextColor(
                    ContextCompat.getColor(
                        mDescriptionTV.context,
                        R.color.black
                    )
                )
            }
        }
    }
}
