package com.dhandev.haditsbooks.adapter.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.haditsbooks.R

class HadithListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName: TextView = itemView.findViewById(R.id.tv_name)
    val tvAvailable: TextView = itemView.findViewById(R.id.tv_available)
    val tvArab: TextView = itemView.findViewById(R.id.tv_arab)
    val tvId: TextView = itemView.findViewById(R.id.tv_translation)
    val hrLine: View = itemView.findViewById(R.id.hr_line)
    val arrow: ImageView = itemView.findViewById(R.id.arrow)
}