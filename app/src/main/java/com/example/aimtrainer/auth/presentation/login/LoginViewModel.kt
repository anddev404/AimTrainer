package com.example.aimtrainer.auth.presentation.login

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.aimtrainer.auth.domain.use_case.ValidateEmail
import com.example.aimtrainer.auth.domain.use_case.ValidatePassword
import com.example.aimtrainer.auth.presentation.FormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private val _login = MutableStateFlow(FormState())
    val login = _login.asStateFlow()

    private val _password = MutableStateFlow(FormState())
    val password = _password.asStateFlow()

    fun changeLogin(login: TextFieldValue) {
        _login.value = _login.value.copy(textFieldValue = login)
    }

    fun changePassword(password: TextFieldValue) {
        _password.value = _password.value.copy(textFieldValue = password)
    }

    fun validateAndLogin() {
        val isValidEmail = validateEmail.execute(_login.value.textFieldValue.text)
        val isValidPassword = validatePassword.execute(_password.value.textFieldValue.text)

        _login.value = _login.value.copy(validationResult = isValidEmail)
        _password.value = _password.value.copy(validationResult = isValidPassword)

        if (_login.value.validationResult?.successful == false || _password.value.validationResult?.successful == false) {
            return
        }
    }
}