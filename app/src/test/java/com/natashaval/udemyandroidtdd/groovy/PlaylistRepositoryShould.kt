package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRaw
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
  private val mapper: PlaylistMapper = mock()
  private val playlists = mock<List<Playlist>>()
  private val playlistsRaw = mock<List<PlaylistRaw>>()
  private val exception = RuntimeException("Something went wrong")

  @Test
  fun getPlaylistsFromService() = runTest {
    val repository = mockSuccessfulCase()

    repository.getPlaylists()

    verify(service, times(1)).fetchPlaylists()
  }

  @Test
  fun emitMappedPlaylistsFromService() = runTest {
    val repository = mockSuccessfulCase()
    assertEquals(playlists, repository.getPlaylists().first().getOrNull())
  }

  @Test
  fun propagateErrors() = runTest {
    val repository = mockFailureCase()
    assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
  }

  @Test
  fun delegateBusinessLogicToMapper() = runTest {
    val repository = mockSuccessfulCase()

    repository.getPlaylists().first()

    verify(mapper, times(1)).invoke(playlistsRaw)
  }

  private suspend fun mockFailureCase(): PlaylistRepository {
    whenever(service.fetchPlaylists()).thenReturn(
      flow {
        emit(Result.failure<List<PlaylistRaw>>(exception))
      }
    )

    return PlaylistRepository(service, mapper)
  }

  private suspend fun mockSuccessfulCase(): PlaylistRepository {
    whenever(service.fetchPlaylists()).thenReturn(
      flow {
        emit(Result.success(playlistsRaw))
      }
    )
    whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)

    return PlaylistRepository(service, mapper)
  }
}