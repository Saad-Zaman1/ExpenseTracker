package com.saad.expensemanager.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExpenseEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun userDao(): UserDao

//    companion object {
//        private var INSTANCE: DatabaseHelper? = null
//
//        fun getDatabase(context: Context): DatabaseHelper {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE = Room.databaseBuilder(
//                        context,
//                        DatabaseHelper::class.java,
//                        "Expense"
//                    )
//                        .build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
}