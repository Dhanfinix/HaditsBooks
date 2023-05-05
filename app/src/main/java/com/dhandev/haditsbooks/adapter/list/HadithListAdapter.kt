package com.dhandev.haditsbooks.adapter.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.haditsbooks.R
import com.dhandev.haditsbooks.data.remote.response.DataItem
import com.dhandev.haditsbooks.data.remote.response.Datas
import com.dhandev.haditsbooks.data.remote.response.HadithsItem

class HadithListAdapter: RecyclerView.Adapter<HadithListViewHolder>() {

    private val list: MutableList<HadithsItem> = mutableListOf()
    var delegate: HadithListDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithListViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hadith_list_item, parent, false)
        return HadithListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HadithListViewHolder, position: Int) {
        holder.tvName.text = holder.itemView.resources.getString(R.string.nomor, list[position].number.toString())
        holder.tvAvailable.text = list[position].id
        holder.tvArab.text = list[position].arab
        holder.tvId.text = list[position].id

        holder.itemView.setOnClickListener {
            list[position].expand = !list[position].expand
            if (list[position].expand){
                holder.tvAvailable.visibility = View.GONE
                holder.tvArab.visibility = View.VISIBLE
                holder.hrLine.visibility = View.VISIBLE
                holder.tvId.visibility = View.VISIBLE
                holder.arrow.rotation = 90f
            } else {
                holder.tvAvailable.visibility = View.VISIBLE
                holder.tvArab.visibility = View.GONE
                holder.hrLine.visibility = View.GONE
                holder.tvId.visibility = View.GONE
                holder.arrow.rotation = 0f
            }
            list[position].let { it1 -> delegate?.onItemClicked(it1) }
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapter(hadith: List<HadithsItem>) {
        list.clear()
        list.addAll(hadith)
        notifyDataSetChanged()
    }

}