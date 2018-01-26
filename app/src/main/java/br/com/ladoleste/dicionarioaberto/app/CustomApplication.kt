package br.com.ladoleste.dicionarioaberto.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        Stetho.initializeWithDefaults(this)
        Timber.plant(SuperLog())
    }
}