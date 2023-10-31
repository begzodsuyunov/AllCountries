package com.example.allcountries.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allcountries.data.source.remote.dto.response.GetAllResponseItem
import com.example.allcountries.domain.repository.AppRepository
import com.example.allcountries.presentation.viewModels.CountryInfoViewModel
import com.example.allcountries.utils.LoadingType
import com.example.allcountries.utils.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryInfoViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : CountryInfoViewModel, ViewModel() {
    override val countryFlow: MutableSharedFlow<GetAllResponseItem> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    override val failureFlow: MutableSharedFlow<String>
        get() = TODO("Not yet implemented")
    override val successFlow: MutableSharedFlow<Any>
        get() = TODO("Not yet implemented")
    override val loading: MutableSharedFlow<LoadingType>
        get() = TODO("Not yet implemented")
    override val hasConnection: MutableSharedFlow<Boolean>
        get() = TODO("Not yet implemented")
    override val isValidFlow: MutableSharedFlow<Boolean>
        get() = TODO("Not yet implemented")


    init {
        viewModelScope.launch {
            repository.getAllCountries().collect {
                when (it) {
                    is ResultData.Failure -> failureFlow.emit(it.message)
                    is ResultData.HasConnection -> hasConnection.emit(it.state)
                    is ResultData.Loading -> loading.emit(it.state)
                    is ResultData.Success -> countryFlow.emit(it.data!!.first())
                }
            }
        }
    }
}