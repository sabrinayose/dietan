package com.example.healthyapp.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyapp.firebase.AuthRepository
import com.example.healthyapp.firebase.ReloadUserResponse
import com.example.healthyapp.firebase.RevokeAccessResponse
import com.example.healthyapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(
        NetworkResult.Success(
            false
        )
    )
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(NetworkResult.Success(false))

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = NetworkResult.Loading
        reloadUserResponse = authRepository.reloadFirebaseUser()
    }

    val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false
    fun signOut() = authRepository.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = NetworkResult.Loading
        revokeAccessResponse = authRepository.revokeAccess()
    }
}