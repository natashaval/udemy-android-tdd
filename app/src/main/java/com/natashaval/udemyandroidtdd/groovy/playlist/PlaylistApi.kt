package com.natashaval.udemyandroidtdd.groovy.playlist

import com.natashaval.udemyandroidtdd.groovy.details.PlaylistDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistApi {
  @GET("playlists")
  suspend fun fetchAllPlaylists(): List<PlaylistRaw>

  @GET("playlist-details/{id}")
  suspend fun fetchPlaylistDetails(@Path("id") id: String): PlaylistDetails
}