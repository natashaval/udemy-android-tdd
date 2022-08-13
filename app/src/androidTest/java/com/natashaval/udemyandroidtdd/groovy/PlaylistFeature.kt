package com.natashaval.udemyandroidtdd.groovy

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

//  val activityRule = ActivityTestRule(GroovyActivity::class.java) is deprecated
  val activityRule = ActivityScenarioRule(GroovyActivity::class.java)
    @Rule get

  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    Assert.assertEquals("com.natashaval.udemyandroidtdd", appContext.packageName)
  }

  @Test
  fun displayScreenTitle() {
    assertDisplayed("Playlists")
  }
}