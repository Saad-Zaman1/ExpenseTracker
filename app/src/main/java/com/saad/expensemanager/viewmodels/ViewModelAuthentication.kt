package com.saad.expensemanager.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.expensemanager.global.GlobalVariables
import com.saad.expensemanager.model.AddUser
import com.saad.expensemanager.repository.Repository
import com.saad.expensemanager.room.UserEntity
import com.saad.expensemanager.utilities.SharedPrefs
import com.saad.expensemanager.utilities.Validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelAuthentication @Inject constructor(
    private val repository: Repository,
    private val sharedPrefs: SharedPrefs
) :
    ViewModel() {

    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var userPhone = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    var isLoggedin = MutableLiveData<Boolean>().apply { value = false }

    private val sp = sharedPrefs


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
    fun onLogin() {
        val emailError = Validate.validateEmail(userEmail.value ?: "")
        val passwordError = Validate.validatePassword(userPassword.value ?: "")
        val valideEmail = validateEmail(userEmail.value!!)

        if (valideEmail.value?.email != userEmail.value) {
            _toastMessage.postValue(
                "Email not Exists ${
                    userEmail.value
                }"
            )
        }
        if (emailError.isNotEmpty() || passwordError.isNotEmpty()) {
            _errorMessageEmail.postValue(emailError)
            _errorMessagePassword.postValue(passwordError)
        } else {
            _toastMessage.postValue(
                "Email is ${
                    userEmail.value
                }"
            )
            sp.saveString(GlobalVariables.userEmail, userEmail.value ?: "")
            sp.saveBoolean(GlobalVariables.isLoggedIn, true)
            isLoggedin.value = true
            userEmail.value = ""
            userPassword.value = ""
            Log.i("EmailU", "${userEmail.value}")
        }


    }

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
            insertUser(
                UserEntity(
                    0,
                    obj.username,
                    obj.phone,
                    obj.email,
                    obj.password
                )
            )
            sp.saveString(GlobalVariables.userEmail, obj.email)
            sp.saveBoolean(GlobalVariables.isLoggedIn, true)

            isLoggedin.value = true
            userName.value = ""
            userEmail.value = ""
            userPhone.value = ""
            userPassword.value = ""
        }

    }

    fun gotoSignUp() {

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