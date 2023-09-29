package com.saad.expensemanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saad.expensemanager.model.TotalAmount

@Dao
interface ExpenseDao {

    @Insert
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("Select * from expense")
    fun getExpense(): LiveData<List<ExpenseEntity>>

    @Query("Select * from expense where userEmail == :email And isIncome == :expenseType")
    fun getAllExpenseType(email: String, expenseType: String): LiveData<List<ExpenseEntity>>

    @Query("Select amount from expense where userEmail == :email And isIncome == :type ")
    fun getTotalAmount(email: String, type: String): LiveData<List<TotalAmount>>

    @Query("Select * from expense where date >= :date")
    fun getByDate(date: String): LiveData<List<ExpenseEntity>>
}