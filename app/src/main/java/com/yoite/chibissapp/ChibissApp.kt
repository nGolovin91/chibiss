package com.yoite.chibissapp

import android.app.Application
import com.yoite.chibissapp.di.DIManager


class ChibissApp : Application() {

    companion object {
        lateinit var diManager: DIManager
    }

    override fun onCreate() {
        super.onCreate()

        diManager = DIManager(this)
        diManager.appComponent?.inject(this)
    }
}