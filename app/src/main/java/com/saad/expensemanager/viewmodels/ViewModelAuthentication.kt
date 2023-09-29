package com.saad.expensemanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.expensemanager.model.AddUser
import com.saad.expensemanager.repository.Repository
import com.saad.expensemanager.room.UserEntity
import com.saad.expensemanager.utilities.Validate
import kotlinx.coroutines.launch

class ViewModelAuthentication(private val repository: Repository) : ViewModel() {

    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var userPhone = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

//    val sharedPreference = SharedPrefs()


    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _errorMessageName = MutableLiveData<String>()
    val errorMessageName: LiveData<String> = _errorMessageName

    private val _errorMessageEmail = MutableLiveData<String>()
    val errorMessageEmail: LiveData<String> = _errorMessageEmail

    private val _errorMessagePhone = MutableLiveData<String>()
    val errorMessagePhone: LiveData<String> = _errorMessagePhone

    private val _errorMessagePassword = MutableLiveData<String>()
    val errorMessagePassword: LiveData<String> = _errorMessagePassword


    fun onSave() {

        val obj = AddUser(
            0,
            userName.value ?: "",
            userPhone.value ?: "",
            userEmail.value ?: "",
            userPassword.value ?: "",
        )

        val emailError = Validate.validateEmail(obj.email)
        val passwordError = Validate.validatePassword(obj.password)
        val phoneError = Validate.validatePhone(obj.phone)
        val nameError = Validate.validateName(obj.username)

        if (emailError.isNotEmpty() || passwordError.isNotEmpty() || phoneError.isNotEmpty() || nameError.isNotEmpty()) {
            _errorMessageEmail.postValue(emailError)
            _errorMessagePassword.postValue(passwordError)
            _errorMessagePhone.postValue(phoneError)
            _errorMessageName.postValue(nameError)
        } else {
            _toastMessage.postValue("SignUp clicked name is ${userName.value}")
            insertUser(
                UserEntity(
                    0,
                    obj.username,
                    obj.phone,
                    obj.email,
                    obj.password
                )
            )

            userName.value = ""
            userEmail.value = ""
            userPhone.value = ""
            userPassword.value = ""
        }

    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun validateEmail(email: String): LiveData<UserEntity> {
        return repository.validateEmail(email)
    }

    fun validatePassword(password: String): LiveData<UserEntity> {
        return repository.validatePassword(password)
    }

}