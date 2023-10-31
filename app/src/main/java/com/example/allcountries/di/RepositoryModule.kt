package com.example.allcountries.di

import com.example.allcountries.data.repositoryImpl.AppRepositoryImpl
import com.example.allcountries.domain.repository.AppRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun appRepositoryBind(impl: AppRepositoryImpl) : AppRepository
}