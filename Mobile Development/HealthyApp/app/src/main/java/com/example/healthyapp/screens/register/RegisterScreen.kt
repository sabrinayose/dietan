package com.example.healthyapp.screens.register

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.healthyapp.util.Constants.Companion.TAG
import com.example.healthyapp.util.NetworkResult
import com.example.healthyapp.util.Utils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit,
    navigateToForgotPassword: () -> Unit
) {
    val gap = 16.dp
    val emailState = remember{ mutableStateOf(TextFieldValue()) }
    val passwordState = remember{ mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    val modMaxWith = Modifier.fillMaxWidth()

    Column(modifier = Modifier
        .padding(start = 12.dp, end = 12.dp, top = 30.dp, bottom = 12.dp)
        .fillMaxWidth()){
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
            Button(onClick = {
                Log.d(TAG, "RegisterScreen: ${emailState.value.text}")
                viewModel.signUpWithEmailAndPassword(emailState.value.text, passwordState.value.text)
            }) {
                Text("Register")
            }
            Spacer(modifier = Modifier.height(gap))
            Text(modifier = Modifier.clickable {
                navigateToLoginScreen()
            }, text = "Already have an account ? Sign In")
            NavToForgotPasswordComponent(
                navigateToForgotPassword
            )
        }


    }

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            Utils.showMessage(context, Constants.VERIFY_EMAIL_MESSAGE)
        }
    )
}

@Composable
fun SignUp(
    viewModel: RegisterViewModel = hiltViewModel(),
    sendEmailVerification: () -> Unit,
    showVerifyEmailMessage: () -> Unit
) {
    when(val signUpResponse = viewModel.signUpResponse) {
        is NetworkResult.Loading -> ProgressBar()
        is NetworkResult.Success -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is NetworkResult.Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}