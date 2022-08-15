package com.natashaval.udemyandroidtdd.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.natashaval.udemyandroidtdd.R
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

  lateinit var viewModel: PlaylistViewModel
  @Inject
  lateinit var viewModelFactory: PlaylistViewModelFactory

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_playlist, container, false)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViewModel()

    viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
      if (playlists.isSuccess && playlists.getOrNull() != null) {
        setupList(view, playlists.getOrNull()!!)
      } else {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
      }
    }
  }

  private fun setupList(
    view: View?,
    playlists: List<Playlist>
  ) {
    with(view as RecyclerView) {
      layoutManager = LinearLayoutManager(context)
      adapter = MyPlaylistRecyclerViewAdapter(playlists)
    }
  }

  private fun setupViewModel() {
    viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
  }

  companion object {
    @JvmStatic
    fun newInstance() =
      PlaylistFragment().apply {
        arguments = Bundle().apply {}
      }
  }
}