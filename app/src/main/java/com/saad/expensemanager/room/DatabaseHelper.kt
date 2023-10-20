package com.saad.expensemanager.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExpenseEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun userDao(): UserDao
}