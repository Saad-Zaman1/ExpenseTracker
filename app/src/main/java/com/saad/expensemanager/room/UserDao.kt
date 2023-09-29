package com.saad.expensemanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert()
    suspend fun insertUser(user: UserEntity): Long


    @Query("select * from users")
    fun getAllUsers(): List<UserEntity>

    @Query("select * from users where email = :email")
    fun validateEmail(email: String): LiveData<UserEntity>

    @Query("select * from users where password = :pass")
    fun validatePassword(pass: String): LiveData<UserEntity>

}