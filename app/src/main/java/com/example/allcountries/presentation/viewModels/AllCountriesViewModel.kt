package com.example.allcountries.presentation.viewModels

import com.example.allcountries.data.source.remote.dto.response.GetAllResponse
import kotlinx.coroutines.flow.Flow

interface AllCountriesViewModel : BaseViewModel{
    val countries: Flow<GetAllResponse>

    fun getAllCountries()
}