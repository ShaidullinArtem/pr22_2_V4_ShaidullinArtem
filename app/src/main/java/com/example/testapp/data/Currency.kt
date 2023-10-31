package com.example.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Currency(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val charCode: String,
    val value: Double
)