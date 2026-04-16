package ie.setu.vegi.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class VegiMainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.i("Starting Vegi Application")

    }
}
