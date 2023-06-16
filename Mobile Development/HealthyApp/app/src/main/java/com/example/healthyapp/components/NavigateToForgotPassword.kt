package com.example.healthyapp.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavToForgotPasswordComponent(navigateToForgotPassword: () -> Unit){
    Text(text = "Forgot your password ? Reset now !", modifier = Modifier.clickable {
        navigateToForgotPassword()
    })
}