package com.natashaval.udemyandroidtdd.unittests

import junit.framework.Assert.assertEquals
import org.junit.Test

class EngineShould {

  private val engine = Engine(15, false)

  @Test
  fun engineTurnsOn() {
    engine.turnOn()
    assertEquals(true, engine.isTurnedOn)
    assertEquals(95, engine.temperature)
  }

  @Test
  fun engineTurnsOff() {
    engine.turnOff()
    assertEquals(false, engine.isTurnedOn)
    assertEquals(15, engine.temperature)
  }
}