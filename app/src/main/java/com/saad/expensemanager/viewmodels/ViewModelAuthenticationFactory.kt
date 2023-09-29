package com.saad.expensemanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saad.expensemanager.repository.Repository

class ViewModelAuthenticationFactory(private val userRepository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.saad.expensemanager.viewmodels.ViewModelAuthentication(userRepository) as T
    }
}