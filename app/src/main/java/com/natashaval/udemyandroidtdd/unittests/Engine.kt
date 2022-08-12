package com.natashaval.udemyandroidtdd.unittests

import android.util.Log
import kotlinx.coroutines.delay

class Engine(
  var temperature: Int,
  var isTurnedOn: Boolean
) {
  suspend fun turnOn() {
    isTurnedOn = true
    delay(6000)
    temperature  = 95

//    Log.d("COURSE",  "engine turns on")
  }

  fun turnOff() {
    isTurnedOn = false
    temperature = 15
  }
}