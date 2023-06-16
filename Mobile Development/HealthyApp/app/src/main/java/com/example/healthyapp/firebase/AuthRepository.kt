package com.example.healthyapp.firebase

import com.example.healthyapp.util.NetworkResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = NetworkResult<Boolean>
typealias SendEmailVerificationResponse = NetworkResult<Boolean>
typealias SignInResponse = NetworkResult<Boolean>
typealias ReloadUserResponse = NetworkResult<Boolean>
typealias SendPasswordResetEmailResponse = NetworkResult<Boolean>
typealias RevokeAccessResponse = NetworkResult<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>


interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse
    suspend fun sendEmailVerification(): SendEmailVerificationResponse
    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse
    suspend fun reloadFirebaseUser(): ReloadUserResponse
    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse
    fun signOut()
    suspend fun revokeAccess(): RevokeAccessResponse
    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
}