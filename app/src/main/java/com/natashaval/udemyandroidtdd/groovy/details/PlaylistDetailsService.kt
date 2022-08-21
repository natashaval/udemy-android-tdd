package com.natashaval.udemyandroidtdd.groovy.details

import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor(
  private val playlistApi: PlaylistApi
) {
  suspend fun fetchPlaylistDetails(id: String): Flow<Result<PlaylistDetails>> {
    return flow { emit(Result.success(playlistApi.fetchPlaylistDetails(id))) }
      .catch {
        emit(Result.failure(RuntimeException("Something went wrong")))
      }
  }
}
