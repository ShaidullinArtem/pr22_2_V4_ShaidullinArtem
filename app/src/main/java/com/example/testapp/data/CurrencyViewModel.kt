package com.example.testapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(application: Application): AndroidViewModel(application) {

    private val readAll: LiveData<List<Currency>>
    private val repository: CurrencyRepository

    init {
        val currencyDao = CurrencyDatabase.getDatabase(application).currencyDao()
        repository = CurrencyRepository(currencyDao)
        readAll = repository.readAll
    }

    fun addCurrency(currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCurrency(currency)
        }
    }
}