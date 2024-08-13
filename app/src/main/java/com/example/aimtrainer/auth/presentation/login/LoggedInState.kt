package com.example.aimtrainer.auth.presentation.login

import com.example.aimtrainer.auth.domain.model.User
import com.google.firebase.auth.FirebaseUser

data class LoggedInState(
    val loggingInProgress: Boolean = false,
    val user: FirebaseUser? = null,
    val error: String? = null
)
