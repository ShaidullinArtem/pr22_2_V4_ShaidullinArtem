package com.example.testapp.data

import androidx.lifecycle.LiveData

class CurrencyRepository(private val currencyDao: CurrencyDao) {

    val readAll: LiveData<List<Currency>> = currencyDao.readAll()

    suspend fun addCurrency(currency: Currency) {
        currencyDao.addCurrency(currency)
    }
}