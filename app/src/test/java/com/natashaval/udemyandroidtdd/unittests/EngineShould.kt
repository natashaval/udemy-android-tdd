package com.natashaval.udemyandroidtdd.unittests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class EngineShould {

  private val engine = Engine(15, false)
  @get:Rule
  var coroutineScopeRule = MainCoroutineScopeRule()

  @Test
  fun turnOn() = runTest {
    engine.turnOn()
    assertTrue(engine.isTurnedOn)
  }

  @Test
  fun riseTheTemperatureWhenItTurnsOn() = runTest {
    engine.turnOn()
    assertEquals(95, engine.temperature)
  }

  @Test
  fun engineTurnsOff() {
    engine.turnOff()
    assertEquals(false, engine.isTurnedOn)
    assertEquals(15, engine.temperature)
  }
}