package com.saad.expensemanager.repository

import androidx.lifecycle.LiveData
import com.saad.expensemanager.model.TotalAmount
import com.saad.expensemanager.room.ExpenseDao
import com.saad.expensemanager.room.ExpenseEntity
import com.saad.expensemanager.room.UserDao
import com.saad.expensemanager.room.UserEntity

class Repository(private val expenseDao: ExpenseDao, private val userDao: UserDao) {

    fun getExpense(): LiveData<List<ExpenseEntity>> {
        return expenseDao.getExpense()
    }

    suspend fun insertExpense(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
    }

    fun getTotalAmount(email: String, type: String): LiveData<List<TotalAmount>> {
        return expenseDao.getTotalAmount(email, type)
    }

    fun getAllExpenseType(email: String, expenseType: String): LiveData<List<ExpenseEntity>> {
        return expenseDao.getAllExpenseType(email, expenseType)
    }

    fun getByDate(date: String): LiveData<List<ExpenseEntity>> {
        return expenseDao.getByDate(date)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun validateEmail(email: String): LiveData<UserEntity> {
        return userDao.validateEmail(email)
    }

    fun validatePassword(password: String): LiveData<UserEntity> {
        return userDao.validatePassword(password)
    }
}