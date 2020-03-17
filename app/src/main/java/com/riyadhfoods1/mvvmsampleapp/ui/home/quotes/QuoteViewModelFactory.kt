package com.riyadhfoods1.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riyadhfoods1.mvvmsampleapp.data.repository.QuotesRepository
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class QuoteViewModelFactory(private val quotesRepository: QuotesRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quotesRepository) as T
    }

}