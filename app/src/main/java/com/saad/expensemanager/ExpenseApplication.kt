package com.saad.expensemanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpenseApplication : Application()
//{
//    lateinit var expenseRepository: Repository
//    override fun onCreate() {
//        super.onCreate()
//        initialize()
//    }
//
//    private fun initialize() {
//        val expenseDatabase = DatabaseHelper.getDatabase(applicationContext)
//        expenseRepository = Repository(expenseDatabase)
//    }
//}