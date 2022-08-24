package com.natashaval.udemyandroidtdd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natashaval.udemyandroidtdd.cicd.RetirementActivity
import com.natashaval.udemyandroidtdd.databinding.ActivityMainBinding
import com.natashaval.udemyandroidtdd.groovy.GroovyActivity

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.groovyButton.setOnClickListener {
      startActivity(Intent(this, GroovyActivity::class.java))
    }

    binding.retirementButton.setOnClickListener {
      startActivity(Intent(this, RetirementActivity::class.java))
    }
  }
}