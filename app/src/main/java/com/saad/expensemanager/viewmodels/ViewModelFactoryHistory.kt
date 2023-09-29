package com.saad.expensemanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saad.expensemanager.repository.Repository

class ViewModelFactoryHistory(private val experseRepository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.saad.expensemanager.viewmodels.ViewModelHistory(experseRepository) as T
    }
}