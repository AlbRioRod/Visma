package com.visma.technical.ui.current

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.visma.technical.R
import com.visma.technical.databinding.FragmentCurrentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    private val viewModel: CurrentWeatherViewModel by viewModels()
    private lateinit var binding: FragmentCurrentWeatherBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_current_weather, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        getLatLng()

        viewModel.getTemp("-3.703", "40.41")

        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.uiModel.collect {
                when (it){
                    CurrentWeatherViewModel.UiModel.SuccessLoading -> showTemp(viewModel.temp)
                }
            }
        }

    }


    private fun showTemp(temp: String ){

        binding.tvTempValue.text = temp
    }


    private fun getLatLng() {
        val lm =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.getTemp("-3.703", "40.41")
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            viewModel.getTemp(it.latitude.toString(),it.longitude.toString())
        }

    }
}