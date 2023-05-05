package com.dhandev.haditsbooks.adapter.list

import com.dhandev.haditsbooks.data.remote.response.Datas
import com.dhandev.haditsbooks.data.remote.response.HadithsItem

interface HadithListDelegate {
    fun onItemClicked(selected: HadithsItem)
}