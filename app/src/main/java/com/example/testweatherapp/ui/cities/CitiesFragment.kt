package com.example.testweatherapp.ui.cities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data_models.models.network.Status
import com.example.testweatherapp.R
import com.example.testweatherapp.databinding.FragmentCitiesBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CitiesFragment : DaggerFragment(R.layout.fragment_cities) {

    private lateinit var binding: FragmentCitiesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CitiesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: CitiesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCitiesBinding.bind(view)

        binding.rvCities.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = CitiesAdapter(
            viewModel.recyclerViewItemClickCallback
        )
        binding.rvCities.adapter = adapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getCitiesResource.observe(
            viewLifecycleOwner
        ) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.onGetCitiesInfoSuccess(it.data)
                }
                Status.LOADING -> {
                    Log.i("CITIES_RESOURCE", "data is loading")
                }
                Status.ERROR -> {
                    Log.e("CITIES_RESOURCE", "error was occurred")
                }
            }
        }

        viewModel.listOfCity.observe(
            viewLifecycleOwner
        ) {
            (binding.rvCities.adapter as CitiesAdapter).submitList(it)
        }

        viewModel.navigateInfo.observe(
            viewLifecycleOwner
        ) {
            val action = CitiesFragmentDirections.actionCitiesFragmentToCityInfoFragment(it)
            findNavController().navigate(
                action
            )
        }
    }

}