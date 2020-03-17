package com.riyadhfoods1.mvvmsampleapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes:List<Quote>)


    @Query("SELECT * FROM Quote")
    fun getQuotes():LiveData<List<Quote>>


}