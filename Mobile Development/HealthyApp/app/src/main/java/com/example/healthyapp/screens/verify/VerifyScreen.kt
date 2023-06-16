package com.example.healthyapp.screens.verify

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthyapp.components.ProgressBar
import com.example.healthyapp.screens.profile.ProfileViewModel
import com.example.healthyapp.util.Constants.Companion.ALREADY_VERIFIED
import com.example.healthyapp.util.Constants.Companion.SPAM_EMAIL
import com.example.healthyapp.util.NetworkResult
import com.example.healthyapp.util.Utils.Companion.showMessage

@Composable
fun VerifyScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                viewModel.reloadUser()
            },
            text = ALREADY_VERIFIED,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = SPAM_EMAIL,
            fontSize = 15.sp
        )
    }

    ReloadUser(
        navigateToHomeScreen = {
            if (viewModel.isEmailVerified) {
                navigateToHomeScreen()
            } else {
                showMessage(context, "Your email has not been verified yet")
            }
        }
    )
}

@Composable
fun ReloadUser(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    when(val reloadUserResponse = viewModel.reloadUserResponse) {
        is NetworkResult.Loading -> ProgressBar()
        is NetworkResult.Success -> {
            val isUserReloaded = reloadUserResponse.data
            LaunchedEffect(isUserReloaded) {
                if (isUserReloaded) {
                    navigateToHomeScreen()
                }
            }
        }
        is NetworkResult.Failure -> reloadUserResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}