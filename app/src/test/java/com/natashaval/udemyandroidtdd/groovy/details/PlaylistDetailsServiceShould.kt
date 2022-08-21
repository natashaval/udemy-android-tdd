package com.natashaval.udemyandroidtdd.groovy.details

import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistApi
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistDetailsServiceShould: BaseUnitTest() {

  private lateinit var service: PlaylistDetailsService
  private val playlistApi: PlaylistApi = mock()
  private val id = "1"
  private val playlistDetails: PlaylistDetails = mock()
  private val exception = RuntimeException("Something went wrong")

  @Test
  fun fetchPlaylistDetailsFromApi() = runTest {
    mockSuccessfulCase()
    service.fetchPlaylistDetails(id).single()
    verify(playlistApi, times(1)).fetchPlaylistDetails(id)
  }

  @Test
  fun convertValuesToFlowResultAndEmitThem() = runTest {
    mockSuccessfulCase()
    assertEquals(Result.success(playlistDetails), service.fetchPlaylistDetails(id).single())
  }

  @Test
  fun emitErrorResultWhenNetworkFails() = runTest {
    mockFailureCase()
    assertEquals("Something went wrong", service.fetchPlaylistDetails(id).single().exceptionOrNull()?.message)
  }

  private suspend fun mockSuccessfulCase() {
    whenever(playlistApi.fetchPlaylistDetails(id)).thenReturn(
      playlistDetails
    )
    service = PlaylistDetailsService(playlistApi)
  }

  private suspend fun mockFailureCase() {
    whenever(playlistApi.fetchPlaylistDetails(id)).thenThrow(
      exception
    )
    service = PlaylistDetailsService(playlistApi)
  }
}