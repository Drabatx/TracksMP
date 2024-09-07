package com.drabatx.tracksmp

import android.app.Application
import com.drabatx.tracksmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class TracksApp: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TracksApp)
        }
    }
}