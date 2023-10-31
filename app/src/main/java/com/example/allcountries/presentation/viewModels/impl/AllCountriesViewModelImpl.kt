package com.example.allcountries.presentation.viewModels.impl

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allcountries.data.source.remote.dto.response.GetAllResponse
import com.example.allcountries.domain.repository.AppRepository
import com.example.allcountries.presentation.viewModels.AllCountriesViewModel
import com.example.allcountries.utils.LoadingType
import com.example.allcountries.utils.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCountriesViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : AllCountriesViewModel, ViewModel() {

    init {
        getAllCountries()
    }

    override val countries: MutableSharedFlow<GetAllResponse> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    override fun getAllCountries() {
        viewModelScope.launch {
            repository.getAllCountries().collect {
                when (it) {
                    is ResultData.Failure -> failureFlow.emit(it.message)
                    is ResultData.HasConnection -> hasConnection.emit(it.state)
                    is ResultData.Loading -> loading.emit(it.state)
                    is ResultData.Success -> countries.emit(it.data!!)
                }
            }
        }
    }

    override val failureFlow: MutableSharedFlow<String> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val successFlow: MutableSharedFlow<Any> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val loading: MutableSharedFlow<LoadingType> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val hasConnection: MutableSharedFlow<Boolean> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val isValidFlow: MutableSharedFlow<Boolean> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
}