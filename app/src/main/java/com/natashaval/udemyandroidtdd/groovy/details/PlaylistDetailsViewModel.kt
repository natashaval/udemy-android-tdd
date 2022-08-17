package com.natashaval.udemyandroidtdd.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PlaylistDetailsViewModel @Inject constructor(
  private val service: PlaylistDetailsService
) : ViewModel() {

  val playlistDetails: MutableLiveData<PlaylistDetails> = MutableLiveData()

  fun getPlaylistDetails(id: String) {
    service.fetchPlaylistDetails(id)
  }

}
