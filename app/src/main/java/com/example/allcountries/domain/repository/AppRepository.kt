package com.example.allcountries.domain.repository

import com.example.allcountries.data.source.remote.dto.response.GetAllResponse
import com.example.allcountries.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getAllCountries(): Flow<ResultData<GetAllResponse>>
}