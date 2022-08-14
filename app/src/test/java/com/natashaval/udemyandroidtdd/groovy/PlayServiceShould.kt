package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistApi
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistService
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
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
    service.fetchPlaylists()
    verify(playlistApi, times(1)).fetchAllPlaylists()
  }

  @Test
  fun convertValuesToFlowResultAndEmitsThem() = runTest {
    whenever(playlistApi.fetchAllPlaylists()).thenReturn(playlists)
    val service = PlaylistService(playlistApi)
    assertEquals(Result.success(playlists), service.fetchPlaylists())
  }

  @Test
  fun emitsErrorResultWhenNetworkFails() = runTest {
    whenever(playlistApi.fetchAllPlaylists()).thenReturn(playlists)
    val service = PlaylistService(playlistApi)
    assertEquals(Result.failure<List<Playlist>>(exception), service.fetchPlaylists())
  }
}