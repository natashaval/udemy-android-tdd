package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRaw
import javax.inject.Inject

class PlaylistMapper @Inject constructor(): Function1<List<PlaylistRaw>, List<Playlist>> {
  override fun invoke(p1: List<PlaylistRaw>): List<Playlist> {
    TODO("Not yet implemented")
  }

}
