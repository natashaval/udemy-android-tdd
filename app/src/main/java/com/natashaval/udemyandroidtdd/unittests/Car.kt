package com.natashaval.udemyandroidtdd.unittests

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(
  val engine: Engine,
  var fuel: Double
) {
  fun turnOn() {
    fuel -= 0.5
    CoroutineScope(Dispatchers.Main).launch {
      engine.turnOn().collect { temperature ->
        Log.d("COURSE", "collected engine temperature: $temperature")
      }
    }
  }
}