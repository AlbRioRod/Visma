package com.visma.technical.ui.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visma.technical.domain.model.CurrentWeatherDomain
import com.visma.technical.domain.usecase.GetCurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeather
) : ViewModel() {

    var temp = ""

    private val _uiModel: MutableStateFlow<CurrentWeatherViewModel.UiModel> =
        MutableStateFlow(CurrentWeatherViewModel.UiModel.Loading)

    val uiModel: StateFlow<CurrentWeatherViewModel.UiModel> = _uiModel


    fun getTemp(lng: String, lat: String) {
        viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            val response = getCurrentWeather.invoke(lat, lng)
            temp = response.temp
            _uiModel.value = UiModel.SuccessLoading
        }

    }


    sealed class UiModel {
        object Loading : UiModel()
        object SuccessLoading : UiModel()
    }
}