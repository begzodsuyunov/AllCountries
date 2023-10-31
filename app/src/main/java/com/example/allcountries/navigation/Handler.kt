package com.example.allcountries.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

interface Handler {
    val navigationStack: Flow<(NavController) -> Unit>
}