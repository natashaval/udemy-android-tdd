package com.natashaval.udemyandroidtdd.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroovyActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_groovy)
  }
}