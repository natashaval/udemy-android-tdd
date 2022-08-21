package com.natashaval.udemyandroidtdd.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistDetailsViewModel @Inject constructor(
  private val service: PlaylistDetailsService
) : ViewModel() {

  val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

  fun getPlaylistDetails(id: String) {
    viewModelScope.launch {
      service.fetchPlaylistDetails(id)
        .collect {
          playlistDetails.postValue(it)
        }
    }
  }

}
