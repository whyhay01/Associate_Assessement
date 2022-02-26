package com.example.associateassessment.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.associateassessment.data.Repository
import com.example.associateassessment.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application): AndroidViewModel(application) {

//    private lateinit var favoriteUsers:LiveData<List<Item>>

    private val repo: Repository = Repository(application)

    val users = repo.getAllUsers().asLiveData()

    fun addFavorites(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addFavorites(item)
        }
    }

    fun getFavorites():LiveData<List<Item>>{
       return repo.getFavoriteUsers()
    }

//    private val _usersList: MutableLiveData<List<Item>> = MutableLiveData()
//    val userListLiveData: LiveData<List<Item>>
//        get() = _usersList
//
//    fun getUsers(){
//        viewModelScope.launch {
//
//            if (_usersList.value != null) _usersList.postValue(_usersList.value)
//            else{
//                withContext(Dispatchers.IO){
//                    repo.getUsers().collect(FlowCollector {
//                        _usersList.postValue(it)
//                    })
//                }
//            }
//        }
//    }


//    fun insertFavoriteUser(item: Item){
//    viewModelScope.launch(Dispatchers.IO) {
//        repo.insertFavorite(item)
//    }
//}
//    fun getFavoriteUsers():LiveData<List<Item>>{
//
//             favoriteUsers = repo.getFavoriteUsers()
//        Log.d("UserViewModel","favoriteUser: $favoriteUsers")
//        return favoriteUsers
//    }
//
//    fun removeFromFavorite(item: Item){
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.removeFromFavorite(item)
//        }
//
//    }

}