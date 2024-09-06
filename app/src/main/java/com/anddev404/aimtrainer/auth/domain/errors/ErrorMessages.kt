package com.anddev404.aimtrainer.auth.domain.errors

interface ErrorMessages {
    val emailBlank: String
    val emailNotValid: String
    val emailAlreadyInUse: String
    val passwordBlank: String
    val passwordTooShort: String
    val passwordComplexityError: String
    val passwordMismatch: String
    val passwordSameAsOld: String
    val nickBlank: String
    val nickTooShort: String
    val nickTooLong: String
}