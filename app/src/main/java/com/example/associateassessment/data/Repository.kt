package com.example.associateassessment.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.associateassessment.api.NetworkManager
import com.example.associateassessment.domain.Item
import com.example.associateassessment.domain.localdb.UserDao
import com.example.associateassessment.domain.localdb.UserDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(context: Context) {

    private val userDao: UserDao

    init {

        val userDb = UserDataBase.getDatabaseInstance(context)
        userDao = userDb!!.userDao()
    }

    private val service = NetworkManager.getService()

    suspend fun getUsers():Flow<List<Item>>{
        val response = service.getUsers()

        return flow {
            if (response.isSuccessful) emit(
                response.body()?.items!!
            )
            else emit((response.errorBody()?.string()) as List<Item>)

        }
    }

    suspend fun insertFavorite(item: Item){
        userDao.insertFavoriteUser(item)
    }

     fun getFavoriteUsers():LiveData<List<Item>>{
        return userDao.allUsers
    }

    fun removeFromFavorite(item: Item){
        userDao.delete(item)
    }
}