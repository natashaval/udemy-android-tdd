package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistApi
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistService
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlayServiceShould : BaseUnitTest() {
  private lateinit var service: PlaylistService
  private val playlistApi: PlaylistApi = mock()
  private val playlists = mock<List<Playlist>>()
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
    assertEquals("Something went wrong", service.fetchPlaylists().first().exceptionOrNull()?.message)
  }

  private suspend fun mockFailureCase() {
    whenever(playlistApi.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developers"))
    service = PlaylistService(playlistApi)
  }
}