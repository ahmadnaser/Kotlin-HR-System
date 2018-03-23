package com.ahmadnaser.kotlinhrsystem
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.
/**
 * Created by windows on 3/21/2018.
 */

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ClearCacheRequest
import com.android.volley.toolbox.Volley


class ANHRSystem:Application(){


    companion object {
        private val TAG = ANHRSystem::class.java.simpleName
        //define signleton app
       @get:Synchronized var instance:ANHRSystem? = null
        private set
    }

    val requestQueue: RequestQueue? = null
    get() {
        if(field == null){
            return  Volley.newRequestQueue(applicationContext)
        }
        return field
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


fun <T> addToRequestQueue(request: Request<T>){
    request.tag = TAG
    requestQueue?.add(request)
}




}