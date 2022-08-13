package com.natashaval.udemyandroidtdd.unittests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
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
  fun riseTheTemperatureGraduallyWhenItTurnsOn() = runTest {
    val flow: Flow<Int> = engine.turnOn()
    val actual = flow.toList()
    assertEquals(listOf(25, 50, 95), actual)
  }

  @Test
  fun engineTurnsOff() {
    engine.turnOff()
    assertEquals(false, engine.isTurnedOn)
    assertEquals(15, engine.temperature)
  }
}