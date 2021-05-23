package com.visma.technical.ui.province

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visma.technical.domain.model.ProvinceDataDomain
import com.visma.technical.domain.model.ProvinceDomain
import com.visma.technical.domain.model.ProvincesDomain
import com.visma.technical.domain.usecase.GetProvinceData
import com.visma.technical.domain.usecase.GetProvinces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinceDetailViewModel @Inject constructor(
    private val getProvince: GetProvinceData
) : ViewModel() {

    var list = ProvinceDataDomain("",listOf())
    private val _uiModel: MutableStateFlow<UiModel> =
        MutableStateFlow(UiModel.Loading)

    val uiModel: StateFlow<UiModel> = _uiModel

    fun getProvinceData(id: String) {
        viewModelScope.launch {
            _uiModel.value = ProvinceDetailViewModel.UiModel.Loading
            val response = getProvince.invoke(id)
            list = response
            _uiModel.value = ProvinceDetailViewModel.UiModel.SuccessLoading
        }
    }


    sealed class UiModel {
        object Loading : UiModel()
        object SuccessLoading : UiModel()
    }
}