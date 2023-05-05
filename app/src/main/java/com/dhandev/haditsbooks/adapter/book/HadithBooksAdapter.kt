package com.dhandev.haditsbooks.adapter.book

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.haditsbooks.R
import com.dhandev.haditsbooks.data.remote.response.DataItem

class HadithBooksAdapter: RecyclerView.Adapter<HadithViewHolder>() {

    private val list: MutableList<DataItem> = mutableListOf()
    var delegate: HadithDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hadith_book_item, parent, false)
        return HadithViewHolder(view)
    }

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        holder.tvName.text = list[position].name
        holder.tvAvailable.text = holder.itemView.context.getString(R.string.hadits_available, list[position].available.toString())
        holder.itemView.setOnClickListener {
            list[position].let { it1 -> delegate?.onItemClicked(it1) }
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapter(hadith: List<DataItem>) {
        list.clear()
        list.addAll(hadith)
        notifyDataSetChanged()
    }

}