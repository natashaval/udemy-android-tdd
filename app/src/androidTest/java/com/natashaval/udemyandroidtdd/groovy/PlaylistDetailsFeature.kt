package com.natashaval.udemyandroidtdd.groovy

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.groovy.playlist.idlingResource
import com.natashaval.udemyandroidtdd.utils.MatcherUtils
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistDetailsFeature : BaseUiTest() {
  @Test
  fun displaysPlaylistNameAndDetails() {
    navigateToPlaylistDetails()

    assertDisplayed(R.id.playlist_name)
    assertDisplayed("Hard Rock Cafe")
    assertDisplayed(R.id.playlist_details)
    assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
  }

  private fun navigateToPlaylistDetails() {
    Espresso.onView(
      CoreMatchers.allOf(
        ViewMatchers.withId(R.id.playlist_image),
        ViewMatchers.isDescendantOfA(
          MatcherUtils.nthChildOf(
            ViewMatchers.withId(R.id.playlists_list),
            0
          )
        )
      )
    ).perform(ViewActions.click())
  }

  @Test
  fun displayLoaderWhileFetchingPlaylistDetails() {
    navigateToPlaylistDetails()
    IdlingRegistry.getInstance().unregister(idlingResource)
    assertDisplayed(R.id.details_loader)
  }

  @Test
  fun hidesLoader() {
    navigateToPlaylistDetails()
    assertNotDisplayed(R.id.details_loader)
  }
}