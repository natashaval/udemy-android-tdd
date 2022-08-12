package com.natashaval.udemyandroidtdd.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.unittests.Car
import com.natashaval.udemyandroidtdd.unittests.Engine

class PracticeActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val engine = Engine(15, false)
    val car = Car(engine, 20.0)
    car.turnOn()

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_practice)
  }
}