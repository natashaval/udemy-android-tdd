package com.natashaval.udemyandroidtdd.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistFragment

class GroovyActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_groovy)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .add(R.id.container, PlaylistFragment.newInstance())
        .commit()
    }
  }
}