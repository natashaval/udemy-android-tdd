package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.groovy.playlist.Playlist
import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRaw
import javax.inject.Inject

class PlaylistMapper @Inject constructor(): Function1<List<PlaylistRaw>, List<Playlist>> {
  override fun invoke(playlistRaw: List<PlaylistRaw>): List<Playlist> {
    return playlistRaw.map {
      val image = when(it.category) {
        "rock" -> R.mipmap.rock
        else -> R.mipmap.playlist
      }
      Playlist(it.id, it.name, it.category, image)
    }
  }

}
