package com.visma.technical.ui.province

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.recyclerview.widget.LinearLayoutManager
import com.visma.technical.R
import com.visma.technical.databinding.FragmentCurrentWeatherBinding
import com.visma.technical.databinding.FragmentProvincesBinding
import com.visma.technical.domain.model.ProvincesDomain
import com.visma.technical.ui.current.CurrentWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProvincesFragment : Fragment() {

    private val viewModel: ProvincesViewModel by viewModels()
    private lateinit var binding: FragmentProvincesBinding
    private lateinit var provincesAdapter: ProvinceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_provinces, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.rvProvinces.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            provincesAdapter = ProvinceAdapter(viewModel.list.provinces)

            adapter = provincesAdapter
            provincesAdapter.notifyDataSetChanged()
        }

        viewModel.getProvinces()

        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.uiModel.collect {
                when (it) {
                    ProvincesViewModel.UiModel.SuccessLoading -> showProvinces(viewModel.list)
                }
            }
        }
    }


    fun showProvinces(list: ProvincesDomain) {

        provincesAdapter = ProvinceAdapter(list.provinces)
        binding.rvProvinces.adapter = provincesAdapter
        provincesAdapter.notifyDataSetChanged()

    }

}