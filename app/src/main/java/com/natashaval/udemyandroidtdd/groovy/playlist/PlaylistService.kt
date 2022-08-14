package com.natashaval.udemyandroidtdd.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlaylistService(
  private val playlistApi: PlaylistApi
) {
  suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
    return flow {
      emit(Result.success(playlistApi.fetchAllPlaylists()))
    }.catch {
      emit(Result.failure(RuntimeException("Something went wrong")))
    }
  }
}
