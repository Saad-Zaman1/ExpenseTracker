package com.saad.expensemanager.model

data class AddUser(
    val id: Long,
    val username: String,
    val phone: String,
    val email: String,
    val password: String,
)