package com.example.weatherapp.presentation.ui.fragments.hours

import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.presentation.base.BaseFragment
import com.example.weatherapp.presentation.ui.fragments.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoursFragment : BaseFragment(R.layout.fragment_hours) {

    private val binding by viewBinding(FragmentHoursBinding::bind)
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter by lazy { HoursAdapter(requireContext()) }

    override fun initialize() {
        mainViewModel.forecast(getString(R.string.london), 3)
        binding.rvHours.adapter = adapter
    }

    override fun setupSubscribers() {
        mainViewModel.forecastFilterState.collectUIState(
            state = {},
            onSuccess = {
                adapter.submitList(it.forecast.forecastday[1].hour)
            },
        )
    }
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}