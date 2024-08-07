package com.example.aimtrainer.auth.domain.use_case

import android.util.Patterns

class ValidateEmail(private val errorMessages: ErrorMessages) {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.emailBlank)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.emailNotValid)

        }
        return ValidationResult(true)
    }
}