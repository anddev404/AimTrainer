package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.aimtrainer.ui.theme.Tab_Bottom_Gradient

@Composable
fun StyledTextField(
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    isError: Boolean = false,
    supportingText: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        isError = isError,
        supportingText = supportingText,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun StyledTextFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Tab_Bottom_Gradient),
        contentAlignment = Alignment.Center
    ) {
        StyledTextField(
            isError = true,
            supportingText = { Text(text = "error") })
    }
}
