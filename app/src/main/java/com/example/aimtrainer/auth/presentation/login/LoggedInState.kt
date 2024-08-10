package com.example.aimtrainer.auth.presentation.login

import com.example.aimtrainer.auth.domain.model.User

data class LoggedInState(
    val loggingInProgress: Boolean = false,
    val user: User? = null,
    val error: String? = null
)
