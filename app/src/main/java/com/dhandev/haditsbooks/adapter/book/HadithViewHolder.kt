package com.dhandev.haditsbooks.adapter.book

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.haditsbooks.R

class HadithViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName: TextView = itemView.findViewById(R.id.tv_name)
    val tvAvailable: TextView = itemView.findViewById(R.id.tv_available)
}