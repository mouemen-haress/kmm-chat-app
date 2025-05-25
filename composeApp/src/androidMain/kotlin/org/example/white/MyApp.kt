package org.example.white

import android.app.Application

class MyApp : Application() {

    companion object {
        var instance: MyApp? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}