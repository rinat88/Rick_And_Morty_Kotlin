package com.example.rickandmortykotlin.presentation.ui.fragment.episode.detailEpisode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.rick_and_morty_kotlin.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortykotlin.common.base.BaseFragment
import com.example.rickandmortykotlin.common.resource.Resource
import com.example.rickandmortykotlin.presentation.state.UIState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeDetailFragment :
    BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>() {

    private val viewModel: EpisodeDetailViewModel by viewModel()
    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    override fun initialize() {
        viewModel.fetchEpisode(args.id)
    }

    override fun setupObservers() = with(binding) {
        viewModel.episodeState.subscribe {
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    it.data.let { data ->
                        name.text = data.name
                        airDate.text = data.air_date
                        episode.text = data.episode
                        url.text = data.url
                        created.text = data.created
                    }
                }
            }
        }
    }
}
