package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRepository
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistService
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistRepositoryShould : BaseUnitTest() {

  private val service: PlaylistService = mock()

  @Test
  fun getPlaylistsFromService() = runTest {
    val repository = PlaylistRepository(service)

    repository.getPlaylists()

    verify(service, times(1)).fetchPlaylists()
  }
}