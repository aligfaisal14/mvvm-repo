package com.riyadhfoods1.mvvmsampleapp.data.network.responses

import com.riyadhfoods1.mvvmsampleapp.data.database.entities.Quote

data class QuotesResponse(val isSuccessful:Boolean, val quotes:List<Quote>)