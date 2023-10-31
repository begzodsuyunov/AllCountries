package com.example.allcountries.data.repositoryImpl

import com.example.allcountries.data.source.remote.dto.response.GetAllResponse
import com.example.allcountries.data.source.remote.service.CountryApi
import com.example.allcountries.domain.repository.AppRepository
import com.example.allcountries.utils.ConnectionUtil
import com.example.allcountries.utils.LoadingType
import com.example.allcountries.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi,
    private val connectionUtil: ConnectionUtil
) : AppRepository {
    override fun getAllCountries(): Flow<ResultData<GetAllResponse>> =
        flow<ResultData<GetAllResponse>> {
            if (connectionUtil.hasConnection()) {
                emit(ResultData.HasConnection(true))
                emit(ResultData.Loading(LoadingType(fullScreen = true)))
                val response = countryApi.getAll()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val message = "Get all countries successfully"
                        emit(ResultData.Success(it))
                        emit(ResultData.Loading(LoadingType(fullScreen = false)))
                    }
                } else emit(ResultData.Failure(response.message()))
            } else {
                emit(ResultData.HasConnection(false))
            }
        }.catch { error ->
            error.message?.let { message -> emit(ResultData.Failure(message)) }
        }.flowOn(Dispatchers.IO)

}