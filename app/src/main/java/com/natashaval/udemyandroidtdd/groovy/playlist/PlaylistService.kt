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
  suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
    try {
      playlistApi.fetchAllPlaylists()
      return flow { emit(Result.success(playlistApi.fetchAllPlaylists())) }
    } catch (ex: Exception) {
      Log.e("COURSE", ex.message.toString())
      return flow { emit(Result.failure(RuntimeException("Something went wrong"))) }
    }
  }
}
