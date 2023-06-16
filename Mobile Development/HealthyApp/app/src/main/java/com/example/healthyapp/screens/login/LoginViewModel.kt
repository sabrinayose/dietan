package com.example.healthyapp.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyapp.firebase.AuthRepository
import com.example.healthyapp.firebase.SignInResponse
import com.example.healthyapp.util.Constants.Companion.TAG
import com.example.healthyapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse>(NetworkResult.Success(false))

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = NetworkResult.Loading
        signInResponse = authRepository.firebaseSignInWithEmailAndPassword(email, password)
    }
}