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
import com.visma.technical.databinding.FragmentProvinceDetailBinding
import com.visma.technical.databinding.FragmentProvincesBinding
import com.visma.technical.domain.model.ProvinceDataDomain
import com.visma.technical.domain.model.ProvincesDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProvincesDetailFragment : Fragment() {

    private val viewModel: ProvinceDetailViewModel by viewModels()
    private lateinit var binding: FragmentProvinceDetailBinding
    private lateinit var citiesAdapter: CityAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_province_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.rvCities.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            citiesAdapter = CityAdapter(viewModel.list.cities)

            adapter = citiesAdapter
            citiesAdapter.notifyDataSetChanged()
        }

        viewModel.getProvinceData("01")

        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.uiModel.collect {
                when (it) {
                    ProvinceDetailViewModel.UiModel.SuccessLoading -> showCities(viewModel.list)
                }
            }
        }
    }


    fun showCities(list: ProvinceDataDomain) {

        citiesAdapter = CityAdapter(list.cities)
        binding.rvCities.adapter = citiesAdapter
        citiesAdapter.notifyDataSetChanged()

    }
}
