package com.anddev404.aimtrainer.auth.presentation.registration.presentation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anddev404.aimtrainer.auth.domain.repository.AuthRepository
import com.anddev404.aimtrainer.auth.presentation.FormState
import com.anddev404.aimtrainer.auth.presentation.login.LoggedInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _isRegistered = MutableStateFlow(LoggedInState())
    val isRegistered = _isRegistered.asStateFlow()

    fun register() {
        _isRegistered.value = LoggedInState(true)

        viewModelScope.launch {
            authRepository.signUp(
                "qwerty${Random.nextInt(100)}@gmail.com", "Test1234.", "nick_${Random.nextInt()}"
            ).onSuccess {
                _isRegistered.value = LoggedInState(false, it)
            }.onFailure {
                _isRegistered.value = LoggedInState(false, error = "${it.message}")
            }
        }
    }
}