package com.android.vlad

import android.app.Application
import com.android.vlad.modules.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyLocationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyLocationsApplication)
            modules(appModules)
        }
    }
}