package com.natashaval.udemyandroidtdd.groovy

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.natashaval.udemyandroidtdd.groovy.playlist.idlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseUiTest {

  val activityRule = ActivityScenarioRule(GroovyActivity::class.java)
    @Rule get

  @Before
  fun setup() {
    IdlingRegistry.getInstance().register(idlingResource)
    // block ui test before ui finish fetching API, so no need thread sleep
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(idlingResource)
  }
}