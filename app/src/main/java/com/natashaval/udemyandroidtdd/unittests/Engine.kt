package com.natashaval.udemyandroidtdd.unittests

class Engine(
  var temperature: Int,
  var isTurnedOn: Boolean
) {
  fun turnOn() {
    isTurnedOn = true
    temperature  = 95
  }

  fun turnOff() {
    isTurnedOn = false
    temperature = 15
  }
}