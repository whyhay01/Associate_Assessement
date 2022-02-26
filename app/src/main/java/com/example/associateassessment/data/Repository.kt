package com.example.associateassessment.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.associateassessment.api.NetworkManager
import com.example.associateassessment.domain.Item
import com.example.associateassessment.domain.localdb.UserDao
import com.example.associateassessment.domain.localdb.UserDataBase
import com.example.associateassessment.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(context: Context) {

    private val userDao: UserDao

    private var userDb: UserDataBase? = UserDataBase.getDatabaseInstance(context)

    init {

        userDao = userDb!!.userDao()
    }

    private val service = NetworkManager.getService()

    fun getAllUsers() = networkBoundResource(
        query = {
            userDao.getAllUsers()
        }, fetch = {
            service.getUsers()
        },
        saveFetchResult = { users ->
                userDb?.withTransaction {
//                userDao.deleteAllUsers()
                    userDao.insertUsers(users.items)
                }
            },
        shouldFetch = {userInDB ->
            userInDB.isEmpty()

        }

    )

    suspend fun addFavorites(item: Item){
        userDao.addFavorite(item)
    }

    fun getFavoriteUsers():LiveData<List<Item>>{
        return userDao.getFavoriteUsers()
    }
}

//    suspend fun getUsers():Flow<List<Item>>{
//        val response = service.getUsers()
//
//        return flow {
//            if (response.isSuccessful) emit(
//                response.body()?.items!!
//            )
//            else emit((response.errorBody()?.string()) as List<Item>)
//
//        }
//    }
//
//    suspend fun insertFavorite(item: Item){
//        userDao.insertUsers(item)
//    }
//
//     fun getFavoriteUsers():LiveData<List<Item>>{
//        return userDao.allUsers
//    }
//
//    fun removeFromFavorite(item: Item){
//        userDao.delete(item)
//    }
//}