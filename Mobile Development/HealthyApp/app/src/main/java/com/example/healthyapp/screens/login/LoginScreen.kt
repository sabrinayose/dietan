package com.example.healthyapp.screens.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthyapp.components.NavToForgotPasswordComponent
import com.example.healthyapp.components.ProgressBar
import com.example.healthyapp.util.Constants
import com.example.healthyapp.util.NetworkResult.*
import com.example.healthyapp.util.Utils.Companion.showMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(   viewModel: LoginViewModel = hiltViewModel(),
                    navigateToRegisterScreen: () -> Unit,
                    navigateToForgotPassword: () -> Unit) {
    val gap = 16.dp
    val emailState = remember{ mutableStateOf(TextFieldValue()) }
    val passwordState = remember{ mutableStateOf(TextFieldValue()) }

    val modMaxWith = Modifier.fillMaxWidth()

    Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 30.dp, bottom = 12.dp).fillMaxWidth()){
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(gap))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Enter password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(gap))
        Column(
            modifier = modMaxWith, horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    viewModel.signInWithEmailAndPassword(emailState.value.text, passwordState.value.text)
                }
            ) {
                Text("Sign In")
            }
            Spacer(modifier = Modifier.width(gap))
            Text(
                modifier = Modifier.clickable { navigateToRegisterScreen() },
                text = "Don't have an account? Sign Up"
            )
            NavToForgotPasswordComponent(
                navigateToForgotPassword
            )
        }
    }

    Login()
}

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
){
    val context = LocalContext.current

    when(val signInResponse = viewModel.signInResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                Log.e(Constants.TAG, "signInWithEmailAndPassword: $signInResponse ")
                showMessage(context, e.message)
            }
        }
    }
}