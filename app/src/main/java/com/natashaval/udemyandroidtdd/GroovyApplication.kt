package com.natashaval.udemyandroidtdd

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GroovyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    AppCenter.start(
      this,
      "2cc2a2e6-a2fc-46b6-a658-b8996dce7935",
      Analytics::class.java,
      Crashes::class.java
    )
  }
}