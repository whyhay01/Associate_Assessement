package com.example.associateassessment.domain.localdb

import android.content.Context
import androidx.room.*
import com.example.associateassessment.domain.Item

@Database(entities = [Item::class], version = 1)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        private var userDatabaseInstance: UserDataBase? = null



    fun getDatabaseInstance(context: Context):UserDataBase?{
        if (userDatabaseInstance == null) {

            synchronized(UserDataBase::class.java) {
                if (userDatabaseInstance == null) {
                    userDatabaseInstance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java, "user_database"
                    )
                        .build()
                }
            }
        }

        return userDatabaseInstance
        }

    }

}