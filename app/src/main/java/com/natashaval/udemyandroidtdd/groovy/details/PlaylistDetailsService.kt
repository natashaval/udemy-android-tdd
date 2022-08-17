package com.natashaval.udemyandroidtdd.groovy.details

import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor(
  private val playlistApi: PlaylistApi
) {
  fun fetchPlaylistDetails(id: String): Flow<PlaylistDetails> {
    return flow { playlistApi.fetchPlaylistDetails(id) }
  }
}
