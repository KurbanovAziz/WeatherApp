package com.example.weatherapp.presentation.ui.fragments.days

import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDaysBinding
import com.example.weatherapp.presentation.base.BaseFragment
import com.example.weatherapp.presentation.ui.fragments.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaysFragment : BaseFragment(R.layout.fragment_days) {

    private val binding by viewBinding(FragmentDaysBinding::bind)
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter by lazy { DaysAdapter(requireContext()) }

    override fun initialize() {
        mainViewModel.forecast(getString(R.string.london), 3)
        binding.rvHours.adapter = adapter
    }

    override fun setupSubscribers() {
        mainViewModel.forecastFilterState.collectUIState(
            state = {},
            onSuccess = {
                adapter.submitList(it.forecast.forecastday)
            },
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}