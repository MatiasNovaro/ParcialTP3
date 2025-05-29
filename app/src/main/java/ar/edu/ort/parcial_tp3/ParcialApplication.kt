package ar.edu.ort.parcial_tp3

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ar.edu.ort.parcial_tp3.retro.core.Config

@HiltAndroidApp
class ParcialApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        Config.apiKey = resources.getString(R.string.app_name)
        Config.baseUrl = resources.getString(R.string.app_name)
    }
}