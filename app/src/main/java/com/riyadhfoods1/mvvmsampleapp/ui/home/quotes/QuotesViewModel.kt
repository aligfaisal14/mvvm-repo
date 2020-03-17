package com.riyadhfoods1.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.riyadhfoods1.mvvmsampleapp.data.repository.QuotesRepository
import com.riyadhfoods1.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

   val quotes by lazyDeferred() {
       repository.getQuotes()
   }
}
