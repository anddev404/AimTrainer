package com.anddev404.aimtrainer.auth.presentation.login

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anddev404.aimtrainer.auth.domain.repository.AuthRepository
import com.anddev404.aimtrainer.auth.domain.use_case.ValidateEmail
import com.anddev404.aimtrainer.auth.domain.use_case.ValidatePassword
import com.anddev404.aimtrainer.auth.presentation.FormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginForm =
        MutableStateFlow(FormState(textFieldValue = TextFieldValue("test@test.test")))
    val loginForm = _loginForm.asStateFlow()

    private val _passwordForm = MutableStateFlow(FormState(textFieldValue = TextFieldValue("Test1234.")))
    val passwordForm = _passwordForm.asStateFlow()

    private val _loggedIn = MutableStateFlow(LoggedInState())
    val loggedIn = _loggedIn.asStateFlow()

    fun updateLoginField(login: TextFieldValue) {
        _loginForm.value = _loginForm.value.copy(textFieldValue = login)
    }

    fun updatePasswordField(password: TextFieldValue) {
        _passwordForm.value = _passwordForm.value.copy(textFieldValue = password)
    }

    fun validateAndLogin() {
        val isValidEmail = validateEmail.execute(_loginForm.value.textFieldValue.text)
        val isValidPassword = validatePassword.execute(_passwordForm.value.textFieldValue.text)

        _loginForm.value = _loginForm.value.copy(validationResult = isValidEmail)
        _passwordForm.value = _passwordForm.value.copy(validationResult = isValidPassword)

        if (_loginForm.value.validationResult?.successful == false || _passwordForm.value.validationResult?.successful == false) {
            return
        }

        _loggedIn.value = LoggedInState(true)

        viewModelScope.launch {
            authRepository.signIn(
                _loginForm.value.textFieldValue.text,
                _passwordForm.value.textFieldValue.text
            ).onSuccess {
                _loggedIn.value = LoggedInState(false, user = it)

            }.onFailure {
                _loggedIn.value = LoggedInState(false, error = it.message ?: "unknown error")
            }
        }

    }
}