package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRepository
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistViewModel
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import com.natashaval.udemyandroidtdd.utils.getValueForTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistViewModelShould : BaseUnitTest() {

  private val repository: PlaylistRepository = mock()
  private val playlists = mock<List<Playlist>>()
  private val expected = Result.success(playlists)
  private val exception = RuntimeException("Something went wrong")

  @Test
  fun getPlaylistsFromRepository() = runTest {
    val viewModel = mockSuccessfulCase()

    viewModel.playlists.getValueForTest()

    verify(repository, times(1)).getPlaylists()
  }

  @Test
  fun emitsPlaylistsFromRepository() = runTest {
    val viewModel = mockSuccessfulCase()

    assertEquals(expected, viewModel.playlists.getValueForTest())
  }

  @Test
  fun emitErrorWhenReceiveError() {
    runBlocking {
      whenever(repository.getPlaylists()).thenReturn(
        flow {
          emit(Result.failure<List<Playlist>>(exception))
        }
      )
    }

    val viewModel = PlaylistViewModel(repository)
    assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
  }

  private fun mockSuccessfulCase(): PlaylistViewModel {
    runBlocking {
      whenever(repository.getPlaylists()).thenReturn(
        flow {
          emit(expected)
        }
      )
    }

    return PlaylistViewModel(repository)
  }
}