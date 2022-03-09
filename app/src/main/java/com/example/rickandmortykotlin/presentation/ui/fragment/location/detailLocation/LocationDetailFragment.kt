package com.example.rickandmortykotlin.presentation.ui.fragment.location.detailLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.rick_and_morty_kotlin.databinding.FragmentLocationDetailBinding
import com.example.rickandmortykotlin.common.base.BaseFragment
import com.example.rickandmortykotlin.presentation.state.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailFragment :
    BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding>() {

    private val viewModel: LocationDetailViewModel by viewModel()
    private lateinit var binding: FragmentLocationDetailBinding
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
    }

    private fun initialise() {
        viewModel.fetchLocation(args.id)
    }

    override fun setupObservers() = with(binding) {
        viewModel.locationState.subscribe {
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    it.data.let { data ->
                        name.text = data.name
                        type.text = data.type
                        dimension.text = data.dimension
                        created.text = data.created
                        url.text = data.url
                    }
                }
            }
        }
    }
}