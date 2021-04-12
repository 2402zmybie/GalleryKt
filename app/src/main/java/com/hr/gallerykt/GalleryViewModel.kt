package com.hr.gallerykt

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    //私有化处理
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    //外界访问
    val photoListLive:LiveData<List<PhotoItem>>
    get() = _photoListLive


    fun fetchData() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener<String> {
                var hits = Gson().fromJson(it, Pixabay::class.java).hits
                Log.e("tag",hits.size.toString())
                _photoListLive.value = hits
            },
            Response.ErrorListener {
                Log.e("tag", it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication())?.requestQueue?.add(stringRequest)
    }

    private fun getUrl():String {
        return "https://pixabay.com/api/?key=17921301-974ad23d82135fa91669f2b9f&q=${keyWords.random()}"
    }

    private val keyWords = arrayOf("cat", "dog", "car", "beauty", "photo", "computer", "girl", "flower", "animal")


}