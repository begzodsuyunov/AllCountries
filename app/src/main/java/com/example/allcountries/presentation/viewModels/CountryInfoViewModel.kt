package com.example.allcountries.presentation.viewModels

import com.example.allcountries.data.source.remote.dto.response.GetAllResponseItem
import kotlinx.coroutines.flow.Flow

interface CountryInfoViewModel : BaseViewModel{
    val countryFlow: Flow<GetAllResponseItem>

}