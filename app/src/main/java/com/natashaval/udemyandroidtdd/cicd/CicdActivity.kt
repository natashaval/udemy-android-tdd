package com.natashaval.udemyandroidtdd.cicd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.databinding.ActivityCicdBinding

class CicdActivity : AppCompatActivity() {
  private lateinit var binding: ActivityCicdBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCicdBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.helloButton.setOnClickListener {
      binding.greetingTextView.text = "Hello ${binding.nameEditText.text}! How are you today?"
    }
  }
}
