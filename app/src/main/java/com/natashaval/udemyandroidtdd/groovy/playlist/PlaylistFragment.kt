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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment() {

  lateinit var viewModel: PlaylistViewModel
  lateinit var viewModelFactory: PlaylistViewModelFactory

  private val retrofit = Retrofit.Builder()
    .baseUrl(API_BASE_URL) // please check that it matches your local ip
    .client(OkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  private val api = retrofit.create(PlaylistApi::class.java)

  private val service = PlaylistService(api)
  private val repository = PlaylistRepository(service)

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
    viewModelFactory = PlaylistViewModelFactory(repository)
    viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
  }

  companion object {
    private const val API_BASE_URL = "http://localhost.charlesproxy.com:3000/"

    @JvmStatic
    fun newInstance() =
      PlaylistFragment().apply {
        arguments = Bundle().apply {}
      }
  }
}