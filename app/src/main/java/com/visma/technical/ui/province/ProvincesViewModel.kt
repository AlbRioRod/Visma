package com.visma.technical.ui.province

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visma.technical.domain.model.ProvincesDomain
import com.visma.technical.domain.usecase.GetCurrentWeather
import com.visma.technical.domain.usecase.GetProvinces
import com.visma.technical.ui.current.CurrentWeatherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvincesViewModel @Inject constructor(
    private val getProvinces: GetProvinces
) : ViewModel() {

    var list = ProvincesDomain(listOf())
    private val _uiModel: MutableStateFlow<ProvincesViewModel.UiModel> =
        MutableStateFlow(ProvincesViewModel.UiModel.Loading)

    val uiModel: StateFlow<ProvincesViewModel.UiModel> = _uiModel

    fun getProvinces() {
        viewModelScope.launch {
            _uiModel.value = ProvincesViewModel.UiModel.Loading
            val response = getProvinces.invoke()
            list = response
            _uiModel.value = ProvincesViewModel.UiModel.SuccessLoading
        }
    }


    sealed class UiModel {
        object Loading : UiModel()
        object SuccessLoading : UiModel()
    }
}