package com.natashaval.udemyandroidtdd.groovy.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.natashaval.udemyandroidtdd.PlaylistDetailFragmentArgs
import com.natashaval.udemyandroidtdd.databinding.FragmentPlaylistDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {
  private var _binding: FragmentPlaylistDetailBinding? = null
  private val binding get() = _binding!!

  val args: PlaylistDetailFragmentArgs by navArgs()

  lateinit var viewModel: PlaylistDetailsViewModel

  @Inject
  lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val id = args.playlistId

    setupViewModel()
    viewModel.getPlaylistDetails(id)

    observeLiveData()
  }

  private fun observeLiveData() {
    viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->
      setupUi(playlistDetails)
    }
  }

  private fun setupViewModel() {
    viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistDetailsViewModel::class.java)
  }

  private fun setupUi(playlistDetails: PlaylistDetails?) {
    playlistDetails?.let {
      binding.playlistName.text = it.name
      binding.playlistDetails.text = it.details
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  companion object {
    @JvmStatic fun newInstance() =
      PlaylistDetailFragment()
  }
}