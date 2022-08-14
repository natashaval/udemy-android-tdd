package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRepository
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistService
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistRepositoryShould : BaseUnitTest() {

  private val service: PlaylistService = mock()
  private val playlists = mock<List<Playlist>>()
  private val exception = RuntimeException("Something went wrong")

  @Test
  fun getPlaylistsFromService() = runTest {
    val repository = PlaylistRepository(service)

    repository.getPlaylists()

    verify(service, times(1)).fetchPlaylists()
  }

  @Test
  fun emitPlaylistsFromService() = runTest {
    val repository = mockSuccessfulCase()
    assertEquals(playlists, repository.getPlaylists().first().getOrNull())
  }

  @Test
  fun propagateErrors() = runTest {
    val repository = mockFailureCase()
    assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
  }

  private suspend fun mockFailureCase(): PlaylistRepository {
    whenever(service.fetchPlaylists()).thenReturn(
      flow {
        emit(Result.failure<List<Playlist>>(exception))
      }
    )

    return PlaylistRepository(service)
  }

  private suspend fun mockSuccessfulCase(): PlaylistRepository {
    whenever(service.fetchPlaylists()).thenReturn(
      flow {
        emit(Result.success(playlists))
      }
    )

    return PlaylistRepository(service)
  }
}