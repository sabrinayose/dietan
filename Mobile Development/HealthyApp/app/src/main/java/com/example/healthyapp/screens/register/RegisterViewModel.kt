package com.example.healthyapp.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyapp.firebase.AuthRepository
import com.example.healthyapp.firebase.SendEmailVerificationResponse
import com.example.healthyapp.firebase.SignUpResponse
import com.example.healthyapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(NetworkResult.Success(false))
        private set

    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(NetworkResult.Success(false))
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = NetworkResult.Loading
        signUpResponse = authRepository.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = NetworkResult.Loading
        sendEmailVerificationResponse = authRepository.sendEmailVerification()
    }
}