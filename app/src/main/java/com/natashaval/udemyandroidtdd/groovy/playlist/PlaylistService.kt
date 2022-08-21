package com.natashaval.udemyandroidtdd.groovy.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlaylistService @Inject constructor(
  private val playlistApi: PlaylistApi
) {
  suspend fun fetchPlaylists(): Flow<Result<List<PlaylistRaw>>> {
    return flow { emit(Result.success(playlistApi.fetchAllPlaylists())) }
      .catch {
        emit(Result.failure(RuntimeException("Something went wrong")))
      }
  }
}
