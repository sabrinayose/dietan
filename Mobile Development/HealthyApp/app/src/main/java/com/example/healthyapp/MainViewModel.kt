package com.example.healthyapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyapp.firebase.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    init {
        getAuthState()
    }

    fun getAuthState() = authRepository.getAuthState(viewModelScope)

    val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false
}