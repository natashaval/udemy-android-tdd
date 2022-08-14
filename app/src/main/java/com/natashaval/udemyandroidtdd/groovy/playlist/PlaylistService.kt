package com.natashaval.udemyandroidtdd.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistService(
  private val playlistApi: PlaylistApi
) {
  suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
    playlistApi.fetchAllPlaylists()
    return flow {

    }
  }

}
