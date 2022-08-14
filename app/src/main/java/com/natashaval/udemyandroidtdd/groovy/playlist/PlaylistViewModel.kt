package com.natashaval.udemyandroidtdd.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PlaylistViewModel(
  private val repository: PlaylistRepository
) : ViewModel() {

  val playlists = liveData<Result<List<Playlist>>> {
    emitSource(repository.getPlaylists().asLiveData())
  }
}
