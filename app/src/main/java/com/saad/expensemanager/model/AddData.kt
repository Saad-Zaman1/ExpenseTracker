package com.saad.expensemanager.model

data class AddData(
    val id: Long,
    val userEmail: String,
    val title: String,
    val time: String,
    val date: String,
    val description: String,
    val amount: String,
    val isIncome: Boolean
)