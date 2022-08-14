package com.natashaval.udemyandroidtdd.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.natashaval.udemyandroidtdd.utils.MainCoroutineScopeRule
import com.natashaval.udemyandroidtdd.utils.getValueForTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistViewModelShould {

  @get:Rule
  var corotuinesTestRule = MainCoroutineScopeRule()

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

//  private val viewModel: PlaylistViewModel
  private val repository: PlaylistRepository = mock()
  private val playlists = mock<List<Playlist>>()
  private val expected = Result.success(playlists)

  init {
//    runBlocking {
//      whenever(repository.getPlaylists()).thenReturn(
//        flow {
//          emit(expected)
//        }
//      )
//    }
//
//    viewModel = PlaylistViewModel(repository)
  }

  @Test
  fun getPlaylistsFromRepository() = runTest {
    runBlocking {
      whenever(repository.getPlaylists()).thenReturn(
        flow {
          emit(expected)
        }
      )
    }

    val viewModel = PlaylistViewModel(repository)

    viewModel.playlists.getValueForTest()

    verify(repository, times(1)).getPlaylists()
  }

  @Test
  fun emitsPlaylistsFromRepository() = runTest {
    runBlocking {
      whenever(repository.getPlaylists()).thenReturn(
        flow {
          emit(expected)
        }
      )
    }

    val viewModel = PlaylistViewModel(repository)

    assertEquals(expected, viewModel.playlists.getValueForTest())
  }
}