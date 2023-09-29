package com.saad.expensemanager.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val userEmail: String,
    val title: String,
    val time: String,
    val date: String,
    val description: String,
    val amount: String,
    val isIncome: String
)