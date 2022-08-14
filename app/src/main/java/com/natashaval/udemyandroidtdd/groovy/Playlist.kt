package com.natashaval.udemyandroidtdd.groovy

import com.natashaval.udemyandroidtdd.R

data class Playlist(
  val id: String,
  val name: String,
  val category: String,
  val image: Int = R.mipmap.playlist
)