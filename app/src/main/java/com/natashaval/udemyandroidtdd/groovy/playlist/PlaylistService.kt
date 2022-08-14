package com.natashaval.udemyandroidtdd.groovy.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlaylistService(
  private val playlistApi: PlaylistApi
) {
  suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
//    return flow {
//      emit(Result.success(playlistApi.fetchAllPlaylists()))
//    }.catch {
//      emit(Result.failure(RuntimeException("Something went wrong")))
//    }
    try {
      playlistApi.fetchAllPlaylists()
      return flow { emit(Result.success(playlistApi.fetchAllPlaylists())) }
    } catch (ex: Exception) {
      Log.e("COURSE", ex.message.toString())
      return flow { emit(Result.failure(RuntimeException("Something went wrong"))) }
    }
  }
}
