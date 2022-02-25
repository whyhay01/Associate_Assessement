package com.example.associateassessment.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserModel(
    @SerializedName("incomplete_results") val incompleteResult : Boolean,
    val items: List<Item>,
    @SerializedName("total_count") val totalCount : Int
)

@Entity(tableName = "user")
@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @SerializedName("avatar_url")val avatarUrl: String,
    @SerializedName("html_url")val htmlUrl : String,
    @SerializedName("login") val userName : String,
    @SerializedName("repos_url")val reposUrl: String,
    var isFavorite:Boolean = false

) : Parcelable