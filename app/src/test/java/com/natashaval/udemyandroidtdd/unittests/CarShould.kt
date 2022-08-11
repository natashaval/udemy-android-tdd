package com.natashaval.udemyandroidtdd.unittests

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CarShould {

  private val engine: Engine = mock()
  private val car = Car(engine,5.0)

  @Test
  fun looseFuelWhenItTurnsOn() {
    car.turnOn()

    assertEquals(4.5, car.fuel)
  }

  @Test
  fun turnOnItsEngine() {
    car.turnOn()

    verify(engine, times(1)).turnOn()
  }
}