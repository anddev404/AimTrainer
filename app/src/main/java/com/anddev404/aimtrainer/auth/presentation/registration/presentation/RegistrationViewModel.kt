package com.anddev404.aimtrainer.auth.presentation.registration.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anddev404.aimtrainer.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    fun register() {
        viewModelScope.launch {
            authRepository.signUp(
                "qwerty${Random.nextInt(100)}@gmail.com",
                "Test1234.",
                "nick_${Random.nextInt()}"
            ).onSuccess {

            }.onFailure {
                
            }
        }
    }
}