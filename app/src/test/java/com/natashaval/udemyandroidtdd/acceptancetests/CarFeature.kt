package com.natashaval.udemyandroidtdd.acceptancetests

import com.natashaval.udemyandroidtdd.unittests.Car
import com.natashaval.udemyandroidtdd.unittests.Engine
import junit.framework.Assert.assertEquals
import org.junit.Test

class CarFeature {

  private val engine = Engine(15, false)

  private val car = Car(engine, 6.0)

  @Test
  fun carIsLosingFuelWhenItTurnsOn() {
    car.turnOn()

    assertEquals(5.5, car.fuel)
  }

  @Test
  fun carIsTurningOnItsEngineAndIncreasesTheTemperature() {
    car.turnOn()

//    assertEquals(95, car.engine.temperature)
//    assertTrue(car.engine.isTurnedOn)
  }
}