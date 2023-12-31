package com.example.allcountries.data.source.remote.dto.response

import com.example.allcountries.data.source.remote.dto.response.Currency
import com.example.allcountries.data.source.remote.dto.response.Flags
import com.example.allcountries.data.source.remote.dto.response.Language
import com.example.allcountries.data.source.remote.dto.response.Translations
import java.io.Serializable


data class GetCountryItem(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<Currency>,
    val demonym: String,
    val flag: String,
    val flags: Flags,
    val gini: Double,
    val independent: Boolean,
    val languages: List<Language>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val subregion: String,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: Translations
) : Serializable