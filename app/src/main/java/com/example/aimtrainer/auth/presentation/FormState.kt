package com.example.aimtrainer.auth.presentation

import androidx.compose.ui.text.input.TextFieldValue
import com.example.aimtrainer.auth.domain.use_case.ValidationResult

data class FormState(
    val textFieldValue: TextFieldValue = TextFieldValue(),
    val validationResult: ValidationResult? = null
)