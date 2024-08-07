package com.example.aimtrainer.auth.domain.use_case

class ValidatePassword(private val errorMessages: ErrorMessages) {

    fun execute(password: String): ValidationResult {

        if (password.isBlank()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.passwordBlank)
        }

        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = errorMessages.passwordTooShort
            )
        }
        return ValidationResult(true)
    }
}