package com.drabatx.tracksmp.presentation.view.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.tracksmp.data.domain.usecase.IsLoggedUseCase
import com.drabatx.tracksmp.data.domain.usecase.LoginUseCase
import com.drabatx.tracksmp.data.model.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.drabatx.tracksmp.utils.Result
class LoginViewModel(private val loginUseCase: LoginUseCase, private val isLoggedUseCase: IsLoggedUseCase):ViewModel() {
    enum class LOGIN_STATE {
        INITIAL, ERROR_EMAIL, ERROR_PASSWORD, SUCCESS
    }

    private val _loginMutableStateFlow = MutableStateFlow<Result<LoginResponse>>(Result.Initial)
    val loginStateFlow: StateFlow<Result<LoginResponse>> get() = _loginMutableStateFlow

    private val _isValidData = MutableStateFlow(LOGIN_STATE.INITIAL)
    val isValidData: StateFlow<LOGIN_STATE> get() = _isValidData

    private val _isLoggedStateFlow = MutableStateFlow(false)
    val isLoggedStateFlow: StateFlow<Boolean> get() = _isLoggedStateFlow


    fun resetForm() {
        _isValidData.value = LOGIN_STATE.INITIAL
    }

    fun isValidData(
        emailAddress: String,
        password: String,
    ) {
        return if (!isEmailCorrect(emailAddress)) {
            _isValidData.value = LOGIN_STATE.ERROR_EMAIL
        } else if (!isPasswordCorrect(password)) {
            _isValidData.value = LOGIN_STATE.ERROR_PASSWORD
        } else {
            _isValidData.value = LOGIN_STATE.SUCCESS
            login(emailAddress, password)
        }
    }

    private fun login(userName: String, password: String) {
        viewModelScope.launch {
            try {
                loginUseCase(userName, password).collect { result ->
                    _loginMutableStateFlow.value = result
                }
            } catch (e: Exception) {
                _loginMutableStateFlow.value = Result.Error(Throwable("Failed login: ${e.message}"))
            }
        }
    }

    private fun isEmailCorrect(emailAddress: String): Boolean {
        return !emailAddress.isNullOrBlank()
    }

    fun isLogged(){
        viewModelScope.launch {
            isLoggedUseCase().collect{
                _isLoggedStateFlow.value = it
            }
        }
    }


    private fun isPasswordCorrect(password: String): Boolean {
        return !password.isNullOrBlank()

    }

    fun resetData() {
        _loginMutableStateFlow.value = Result.Initial
        _isValidData.value = LOGIN_STATE.INITIAL
        _isLoggedStateFlow.value = false
    }
}