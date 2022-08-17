package com.natashaval.udemyandroidtdd.groovy.details

import com.natashaval.udemyandroidtdd.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.*

class PlaylistDetailsViewModelShould {

  private val service: PlaylistDetailsService = mock()
  private val playlistDetails: PlaylistDetails = mock()

  @Test
  fun getPlaylistDetailsFromService() = runTest {
    val viewModel = mockSuccessfulCase()
    viewModel.playlistDetails.getValueForTest()

    verify(service, times(1)).fetchPlaylistDetails(any())
  }

  private fun mockSuccessfulCase(): PlaylistDetailsViewModel {
    runBlocking {
      whenever(service.fetchPlaylistDetails(any())).thenReturn(
        flow {
          emit(playlistDetails)
        }
      )
    }

    return PlaylistDetailsViewModel(service)
  }
}