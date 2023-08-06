package com.example.weatherapp.presentation.ui.fragments.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.presentation.base.BaseFragment
import com.example.weatherapp.presentation.ui.fragments.days.DaysFragment
import com.example.weatherapp.presentation.ui.fragments.hours.HoursFragment
import com.example.weatherapp.presentation.ui.fragments.main.adapter.VpAdapter
import com.example.weatherapp.presentation.utils.UIState
import com.example.weatherapp.presentation.utils.loadImage
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val fragmentList = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance()
    )
    private val tabLayoutList = listOf(
        R.string.hours,
        R.string.days
    )

    override fun initialize() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        val hoursTitle = requireContext().getString(tabLayoutList[0])
        val daysTitle = requireContext().getString(tabLayoutList[1])

        val adapter = VpAdapter(requireActivity(), fragmentList)
        binding.vp.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp) { tab, pos ->
            when (pos) {
                0 -> tab.text = hoursTitle
                1 -> tab.text = daysTitle
            }
        }.attach()

        getLocation()
    }

    override fun setupRequests() {

    }

    override fun setupSubscribers() {
        mainViewModel.forecastFilterState.collectUIState(
            state = {
                binding.progressBar.isVisible = true
                binding.layoutMain.isVisible = false
            },
            onSuccess = {
                binding.progressBar.isVisible = false
                binding.layoutMain.isVisible = true
                binding.tvCity.text = it.location.name
                binding.tvCondition.text = it.current.condition.text
                binding.imWeather.loadImage("https:" + it.current.condition.icon)
                binding.tvCurrentTemp.text = getString(R.string.currentTemp, it.current.temp_c)
                binding.tvData.text = it.current.last_updated
                binding.tvMaxMin.text = getString(
                    R.string.maxMin,
                    it.forecast.forecastday[0].day.mintemp_c,
                    it.forecast.forecastday[0].day.maxtemp_c
                )
            },
        )
    }

    override fun setupListeners() {
        binding.ibSync.setOnClickListener {
            getLocation()
        }
    }

    private fun getLocation() {
        val cancellationTokenSource = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mainViewModel.forecast(getString(R.string.london), 3)
            return
        }
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        )
            .addOnCompleteListener {
                mainViewModel.forecast("${it.result.latitude}, ${it.result.longitude}", 3)
            }
    }
}