package com.natashaval.udemyandroidtdd.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistDetailsViewModel : ViewModel() {

  val playlistDetails: MutableLiveData<PlaylistDetails> = MutableLiveData()

  fun getPlaylistDetails(id: String) {
    TODO("Not yet implemented")
  }

}
