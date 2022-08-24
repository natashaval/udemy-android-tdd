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
import androidx.navigation.findNavController
import com.natashaval.udemyandroidtdd.R
import com.natashaval.udemyandroidtdd.databinding.FragmentPlaylistBinding
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
  private var _binding: FragmentPlaylistBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViewModel()

    observeLoader()

    observePlaylists()
  }

  private fun observeLoader() {
    viewModel.loader.observe(this as LifecycleOwner) { loading ->
      when (loading) {
        true -> binding.loader.visibility = View.VISIBLE
        else -> binding.loader.visibility = View.GONE
      }
    }
  }

  private fun observePlaylists() {
    viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
      if (playlists.isSuccess && playlists.getOrNull() != null) {
        setupList(binding.playlistsList, playlists.getOrNull()!!)
      } else {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun setupList(
    view: View?,
    playlists: List<Playlist>
  ) {
    with(view as RecyclerView) {
      layoutManager = LinearLayoutManager(context)
      adapter = MyPlaylistRecyclerViewAdapter(playlists) { id ->
        val action = PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)
        findNavController().navigate(action)
      }
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}