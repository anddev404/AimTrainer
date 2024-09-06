package com.anddev404.aimtrainer.auth.presentation.registration.presentation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anddev404.aimtrainer.auth.domain.repository.AuthRepository
import com.anddev404.aimtrainer.auth.domain.use_case.ValidateEmail
import com.anddev404.aimtrainer.auth.domain.use_case.ValidateNick
import com.anddev404.aimtrainer.auth.domain.use_case.ValidatePassword
import com.anddev404.aimtrainer.auth.presentation.FormState
import com.anddev404.aimtrainer.auth.presentation.login.LoggedInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository, private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword, private val validateNick: ValidateNick
) :
    ViewModel() {

    private val _isRegistered = MutableStateFlow(LoggedInState())
    val isRegistered = _isRegistered.asStateFlow()

    private val _emailForm =
        MutableStateFlow(FormState(textFieldValue = TextFieldValue()))
    val emailForm = _emailForm.asStateFlow()

    private val _passwordForm = MutableStateFlow(FormState(textFieldValue = TextFieldValue()))
    val passwordForm = _passwordForm.asStateFlow()

    private val _nickForm = MutableStateFlow(FormState(textFieldValue = TextFieldValue()))
    val nickForm = _nickForm.asStateFlow()

    fun updateEmailField(email: TextFieldValue) {
        _emailForm.value = _emailForm.value.copy(textFieldValue = email)
    }

    fun updatePasswordField(password: TextFieldValue) {
        _passwordForm.value = _passwordForm.value.copy(textFieldValue = password)
    }

    fun updateNickField(nick: TextFieldValue) {
        _nickForm.value = _nickForm.value.copy(textFieldValue = nick)
    }

    fun validateAndRegister() {
        val isValidEmail = validateEmail.execute(_emailForm.value.textFieldValue.text)
        val isValidPassword = validatePassword.execute(_passwordForm.value.textFieldValue.text)
        val isValidNick = validateNick.execute(_nickForm.value.textFieldValue.text)

        _emailForm.value = _emailForm.value.copy(validationResult = isValidEmail)
        _passwordForm.value = _passwordForm.value.copy(validationResult = isValidPassword)
        _nickForm.value = _nickForm.value.copy(validationResult = isValidNick)

        if (_emailForm.value.validationResult?.successful == false || _passwordForm.value.validationResult?.successful == false || _nickForm.value.validationResult?.successful == false) {
            return
        }

        _isRegistered.value = LoggedInState(true)

        viewModelScope.launch {
            authRepository.signUp(
                _emailForm.value.textFieldValue.text,
                _passwordForm.value.textFieldValue.text,
                _nickForm.value.textFieldValue.text
            ).onSuccess {
                _isRegistered.value = LoggedInState(false, it)
            }.onFailure {
                _isRegistered.value = LoggedInState(false, error = "${it.message}")
            }
        }

    }
}