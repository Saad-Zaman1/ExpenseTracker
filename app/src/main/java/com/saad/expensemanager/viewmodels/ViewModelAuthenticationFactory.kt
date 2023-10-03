//package com.saad.expensemanager.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.saad.expensemanager.repository.Repository
//import com.saad.expensemanager.utilities.SharedPrefs
//
//class ViewModelAuthenticationFactory(
//    private val userRepository: Repository,
//    private val sharedPrefs: SharedPrefs
//) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return com.saad.expensemanager.viewmodels.ViewModelAuthentication(
//            userRepository,
//            sharedPrefs
//        ) as T
//    }
//}