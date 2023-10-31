package com.example.testapp.types

import java.text.DateFormat
import kotlinx.serialization.Serializable

@Serializable
public data class CurrencyData(
    var id: Number,
    var char_code: String,
    var nom: String,
    var nominal: Number,
    var gen: String,
    var genitive_singular: String,
    var nominative_singular: String,
    var accusative_singular: String,
    var nominal_accusative: String,
    var genitive_singular_correct: String,
    var genitive_plural : String,
    var prepositional_plural: String,
    var values: ArrayList<CurrencyValuesData>

)

@Serializable
public data class CurrencyValuesData (
    var rate: Number,
    var date: DateFormat
)


