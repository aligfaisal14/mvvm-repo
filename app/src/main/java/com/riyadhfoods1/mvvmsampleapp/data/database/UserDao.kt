package com.riyadhfoods1.mvvmsampleapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.CURRENT_USER_ID
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.UserEntity


@Dao
interface UserDao{


    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(user:UserEntity):Long

    @Query("SELECT * FROM USERENTITY WHERE uid = $CURRENT_USER_ID")
    fun getUser():LiveData<UserEntity>

}