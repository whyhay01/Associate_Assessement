package com.example.associateassessment.domain.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.associateassessment.domain.Item

@Dao
interface UserDao {

    @Insert
    fun insertFavoriteUser(item: Item)

    @get:Query("SELECT * FROM user")
    val allUsers : LiveData<List<Item>>

    @Delete
    fun delete(item: Item)
}