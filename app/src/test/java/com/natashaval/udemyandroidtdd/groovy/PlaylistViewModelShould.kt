package com.natashaval.udemyandroidtdd.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.natashaval.udemyandroidtdd.utils.MainCoroutineScopeRule
import com.natashaval.udemyandroidtdd.utils.getValueForTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistViewModelShould {

  @get:Rule
  var corotuinesTestRule = MainCoroutineScopeRule()

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  val viewModel: PlaylistViewModel
  val repository: PlaylistRepository = mock()

  init {
    viewModel = PlaylistViewModel()

  }

  @Test
  fun getPlaylistsFromRepository() {
    viewModel.playlists.getValueForTest()

    verify(repository, times(1)).getPlaylists()
  }
}