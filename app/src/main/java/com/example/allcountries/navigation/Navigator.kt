package com.example.allcountries.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.Flow

interface Navigator {
    suspend fun navigateTo(direction: NavDirections)
    suspend fun back()
}