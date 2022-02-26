package com.example.associateassessment.api

import com.example.associateassessment.domain.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("search/users?q=lagos&page=1")
    suspend fun getUsers():UserModel
}