package com.example.loginmvvc.ui.viewmodels

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvc.models.User

class MainViewModel : ViewModel() {
    var errorMessage = ""
    private val _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private val _changeScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val changeScreen: LiveData<Boolean> = _changeScreen

    private val _username: MutableLiveData<String> = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password: MutableLiveData<String> = MutableLiveData("")
    val password: LiveData<String> = _password

    var users = mutableMapOf<String, User>(
        "admin" to User("admin", "admin"),
        "theboss" to User("theboss", "password")
    )

    fun login() {
        if (users.contains(_username.value)) {
            var user = users[_username.value]
            if (user!!.password == _password.value) {
                _changeScreen.value = true
            } else {
                errorMessage = "Username or Password incorrect"
                _showError.value = true
            }
        } else {
            errorMessage = "Username or Password incorrect"
            _showError.value = true
        }
    }

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}