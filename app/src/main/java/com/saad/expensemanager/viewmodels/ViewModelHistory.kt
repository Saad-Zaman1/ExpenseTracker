package com.saad.expensemanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saad.expensemanager.repository.Repository
import com.saad.expensemanager.room.ExpenseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModelHistory @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getByDate(date: String): LiveData<List<ExpenseEntity>> {
        return repository.getByDate(date)
    }


    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate
    fun updateDate(newDate: String) {
        _selectedDate.value = newDate
    }
}