package com.hr.gallerykt

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(private val context:Context){

    companion object {
        private var _INSTANCE:VolleySingleton? = null
        fun getInstance(context:Context):VolleySingleton? {
            if(_INSTANCE == null) {
                synchronized(this) {
                    VolleySingleton(context).also {
                        _INSTANCE = it
                        return _INSTANCE
                    }
                }
            }
            return _INSTANCE
        }
    }
    //得到请求队列
    val requestQueue:RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
}