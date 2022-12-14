package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistApi
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRaw
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistService
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistServiceShould : BaseUnitTest() {
  private lateinit var service: PlaylistService
  private val playlistApi: PlaylistApi = mock()
  private val playlists = mock<List<PlaylistRaw>>()
  private val exception = RuntimeException("Something went wrong")

  @Test
  fun fetchPlaylistsFromApi() = runTest {
    service = PlaylistService(playlistApi)
    service.fetchPlaylists().first()
    verify(playlistApi, times(1)).fetchAllPlaylists()
  }

  @Test
  fun convertValuesToFlowResultAndEmitsThem() = runTest {
    mockSuccessfulCase()
    assertEquals(Result.success(playlists), service.fetchPlaylists().first())
  }

  private suspend fun mockSuccessfulCase() {
    whenever(playlistApi.fetchAllPlaylists()).thenReturn(playlists)
    service = PlaylistService(playlistApi)
  }

  @Test
  fun emitsErrorResultWhenNetworkFails() = runTest {
    mockFailureCase()
    //    assertEquals(Result.failure<List<Playlist>>(exception), service.fetchPlaylists().first())
    assertEquals(exception.message, service.fetchPlaylists().first().exceptionOrNull()?.message)
  }

  private suspend fun mockFailureCase() {
    whenever(playlistApi.fetchAllPlaylists()).thenThrow(exception)
    service = PlaylistService(playlistApi)
  }
}