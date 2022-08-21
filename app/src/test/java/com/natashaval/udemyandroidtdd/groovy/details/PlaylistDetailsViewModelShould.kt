package com.natashaval.udemyandroidtdd.groovy.details

import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import com.natashaval.udemyandroidtdd.utils.captureValues
import com.natashaval.udemyandroidtdd.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*

class PlaylistDetailsViewModelShould : BaseUnitTest() {

  private lateinit var viewModel: PlaylistDetailsViewModel
  private val id = "1"
  private val service: PlaylistDetailsService = mock()
  private val playlistDetails: PlaylistDetails = mock()
  private val expected = Result.success(playlistDetails)
  private val exception = RuntimeException("Something went wrong")
  private val error = Result.failure<PlaylistDetails>(exception)

  @Test
  fun getPlaylistDetailsFromService() = runTest {
    mockSuccessfulCase()
    viewModel.getPlaylistDetails(id)

    viewModel.playlistDetails.getValueForTest()

    verify(service, times(1)).fetchPlaylistDetails(any())
  }

  @Test
  fun emitPlaylistDetailsFromService() = runTest {
    mockSuccessfulCase()
    viewModel.getPlaylistDetails(id)

    assertEquals(expected, viewModel.playlistDetails.getValueForTest())
  }

  @Test
  fun emitErrorWhenServiceFails() = runTest {
    mockErrorCase()
    viewModel.getPlaylistDetails(id)

    assertEquals(error, viewModel.playlistDetails.getValueForTest())
  }

  @Test
  fun showSpinnerWhileLoading() = runTest {
    mockSuccessfulCase()

    viewModel.loader.captureValues {
      viewModel.getPlaylistDetails(id)
      viewModel.playlistDetails.getValueForTest()

      assertEquals(true, values.first())
    }
  }

  @Test
  fun closeLoaderAfterPlaylistDetailsLoad() = runTest {
    mockSuccessfulCase()

    viewModel.loader.captureValues {
      viewModel.getPlaylistDetails(id)
      viewModel.playlistDetails.getValueForTest()

      assertEquals(false, values.last())
    }
  }

  private suspend fun mockErrorCase() {
    whenever(service.fetchPlaylistDetails(id)).thenReturn(
      flow {
        emit(error)
      }
    )
    viewModel = PlaylistDetailsViewModel(service)
    viewModel.getPlaylistDetails(id)
  }

  private fun mockSuccessfulCase() {
    runBlocking {
      whenever(service.fetchPlaylistDetails(any())).thenReturn(
        flow {
          emit(expected)
        }
      )
    }

    viewModel = PlaylistDetailsViewModel(service)
  }
}