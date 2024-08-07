package com.example.aimtrainer.auth.domain.use_case

interface ErrorMessages {
    val emailBlank: String
    val emailNotValid: String
    val emailAlreadyInUse: String
    val passwordBlank: String
    val passwordTooShort: String
    val passwordComplexityError: String
    val passwordMismatch: String
    val passwordSameAsOld: String
}