package com.example.associateassessment.domain.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.associateassessment.domain.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(item: List<Item>)

    @Query("SELECT * FROM user")
    fun getAllUsers() : Flow<List<Item>>

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user WHERE isFavorite = 1 ")
    fun getFavoriteUsers(): Flow<List<Item>>

    @Delete
    fun delete(item: Item)
}