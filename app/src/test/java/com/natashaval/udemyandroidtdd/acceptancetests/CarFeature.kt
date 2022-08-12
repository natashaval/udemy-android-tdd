package com.natashaval.udemyandroidtdd.acceptancetests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.natashaval.udemyandroidtdd.unittests.Car
import com.natashaval.udemyandroidtdd.unittests.Engine
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class CarFeature {

  private val engine = Engine(15, false)

  private val car = Car(engine, 6.0)
  @get:Rule
  var coroutineScopeRule = MainCoroutineScopeRule()

  @Test
  fun carIsLosingFuelWhenItTurnsOn() = runTest {
    car.turnOn()

    assertEquals(5.5, car.fuel)
  }

  @Test
  fun carIsTurningOnItsEngineAndIncreasesTheTemperature() = runTest {
    car.turnOn()
    coroutineScopeRule.advanceTimeBy(6001)

    assertEquals(95, car.engine.temperature)
    assertTrue(car.engine.isTurnedOn)
  }
}