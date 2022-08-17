package com.natashaval.udemyandroidtdd.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.utils.MatcherUtils
import org.hamcrest.CoreMatchers.allOf

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

  @Test
  fun displayListOfPlaylists() {
    Thread.sleep(4000)

    assertRecyclerViewItemCount(R.id.playlists_list, 10)

    onView(
      allOf(
        withId(R.id.playlist_name),
        isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlists_list), 0))
      )
    ).check(matches(withText("Hard Rock Cafe"))).check(matches(isDisplayed()))

    onView(
      allOf(
        withId(R.id.playlist_category),
        isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlists_list), 0))
      )
    ).check(matches(withText("rock"))).check(matches(isDisplayed()))

    onView(
      allOf(
        withId(R.id.playlist_image),
        isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlists_list), 0))
      )
    ).check(matches(withDrawable(R.mipmap.playlist))).check(matches(isDisplayed()))
  }

  @Test
  fun displayLoaderWhileFetchingThePlaylists() {
    assertDisplayed(R.id.loader)
  }

  @Test
  fun hidesLoader() {
    Thread.sleep(4000)

    assertNotDisplayed(R.id.loader)
  }
}