package com.o7.apps

import android.app.Application
import timber.log.Timber

class O7App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}