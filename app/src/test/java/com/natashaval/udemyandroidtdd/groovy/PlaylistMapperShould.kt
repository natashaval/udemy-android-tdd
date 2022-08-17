package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.groovy.playlist.PlaylistRaw
import com.natashaval.udemyandroidtdd.utils.BaseUnitTest
import org.junit.Test
import com.natashaval.udemyandroidtdd.R
import org.junit.Assert.assertEquals

class PlaylistMapperShould : BaseUnitTest() {

  private val playlistRaw = PlaylistRaw("id", "da name", "da category")
  private val playlistRawRock = PlaylistRaw("id", "da name", "rock")
  private val mapper = PlaylistMapper()
  private val playlists = mapper(listOf(playlistRaw))
  private val playlist = playlists[0]

  private val playlistRock = mapper(listOf(playlistRawRock))[0]

  @Test
  fun keepSameId() {
    assertEquals(playlistRaw.id, playlist.id)
  }

  @Test
  fun keepSameName() {
    assertEquals(playlistRaw.name, playlist.name)
  }

  @Test
  fun keepSameCategory() {
    assertEquals(playlistRaw.category, playlist.category)
  }

  @Test
  fun mapDefaultImageWhenNotRock() {
    assertEquals(R.mipmap.playlist, playlist.image)
  }

  @Test
  fun mapRockImageWhenRockCategory() {
    assertEquals(R.mipmap.rock, playlistRock.image)
  }
}