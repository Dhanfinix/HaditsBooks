package com.dhandev.haditsbooks

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.BuildConfig
import com.dhandev.haditsbooks.data.remote.network.ApiConfig
import com.dhandev.haditsbooks.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _hadithId = MutableLiveData<ResponseSpecificId>()
    val hadithId : LiveData<ResponseSpecificId>
        get() = _hadithId
    
    private val _hadithBook = MutableLiveData<ResponseHadithBook>()
    val hadithBook : LiveData<ResponseHadithBook>
        get() = _hadithBook

    private val _hadithList = MutableLiveData<ResponseHadithList>()
    val hadithList : LiveData<ResponseHadithList>
        get() = _hadithList

    private val _darkMode = MutableLiveData<Boolean>()
    val darkMode : LiveData<Boolean>
        get() = _darkMode

    init {
        val randomNum = (0..8).random()
        val randomBooks = arrayOf("muslim", "bukhari", "tirmidzi", "nasai", "abu-daud", "ibnu-majah", "ahmad", "darimi", "malik")[randomNum]
        val booksAvailable = arrayOf(4930, 6638, 3625, 5364, 4419, 4285, 4305, 2949, 1587)[randomNum]
        getData(randomBooks, (1..booksAvailable).random().toString())
    }

    fun getData(id: String, number: String){
        ApiConfig.getApiService()
            .getHadith(id, number)
            .enqueue(object : Callback<ResponseSpecificId> {
                override fun onResponse(
                    call: Call<ResponseSpecificId>,
                    response: Response<ResponseSpecificId>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _hadithId.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ResponseSpecificId>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getDataBook(id: String){
        ApiConfig.getApiService()
            .getHadithBook()
            .enqueue(object : Callback<ResponseHadithBook> {
                override fun onResponse(
                    call: Call<ResponseHadithBook>,
                    response: Response<ResponseHadithBook>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _hadithBook.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ResponseHadithBook>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getDataList(id: String, range: String){
        ApiConfig.getApiService()
            .getHadithList(id, range)
            .enqueue(object : Callback<ResponseHadithList> {
                override fun onResponse(
                    call: Call<ResponseHadithList>,
                    response: Response<ResponseHadithList>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _hadithList.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ResponseHadithList>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getSavedDark(context: Context){
        val preferences = context.getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        val useDarkTheme = preferences.getBoolean("dark_theme", false)

        _darkMode.postValue(useDarkTheme)
    }

    fun setDark(context: Context){
        val preferences = context.getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        val useDarkTheme = preferences.getBoolean("dark_theme", false)

        val editor = preferences.edit()
        editor.putBoolean("dark_theme", !useDarkTheme)
        editor.apply()

        _darkMode.postValue(!useDarkTheme)
    }
}