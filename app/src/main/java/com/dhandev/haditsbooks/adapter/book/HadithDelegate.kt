package com.dhandev.haditsbooks.adapter.book

import com.dhandev.haditsbooks.data.remote.response.DataItem

interface HadithDelegate {
    fun onItemClicked(selected: DataItem)
}