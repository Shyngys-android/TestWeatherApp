package com.example.testweatherapp.ui.city_info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data_models.models.network.Status
import com.example.testweatherapp.R
import com.example.testweatherapp.databinding.FragmentCityInfoBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CityInfoFragment : DaggerFragment(R.layout.fragment_city_info) {

    private val args: CityInfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentCityInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CityInfoViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: WeathersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCityInfoBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvDays.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        adapter = WeathersAdapter()
        binding.rvDays.adapter = adapter

        viewModel.setArgs(args.cityName)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getWeatherInfoResource.observe(
            viewLifecycleOwner
        ) {
            when(it.status) {
                Status.SUCCESS -> {
                    viewModel.onGetWeatherInfoSuccess(it.data)
                }
                Status.LOADING -> {
                    Log.i("WEATHER_RESOURCE", "data is loading")
                }
                Status.ERROR -> {
                    Log.e("WEATHER_RESOURCE", "error was occurred")
                }
            }
        }

        viewModel.listOfDayInfo.observe(
            viewLifecycleOwner
        ) {
            (binding.rvDays.adapter as WeathersAdapter).submitList(it)
        }
    }

}