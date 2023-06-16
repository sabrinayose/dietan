package com.example.healthyapp.screens.forgot

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyapp.firebase.AuthRepository
import com.example.healthyapp.firebase.SendPasswordResetEmailResponse
import com.example.healthyapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
   private val authRepository: AuthRepository
): ViewModel(){
    var sendPasswordResetEmailResponse by mutableStateOf<SendPasswordResetEmailResponse>(NetworkResult.Success(false))

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        sendPasswordResetEmailResponse = NetworkResult.Loading
        sendPasswordResetEmailResponse = authRepository.sendPasswordResetEmail(email)
    }
}