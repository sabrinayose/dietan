package com.example.healthyapp.navigation

import com.example.healthyapp.util.Constants.Companion.FORGOT_PASSWORD_SCREEN
import com.example.healthyapp.util.Constants.Companion.HOME_SCREEN
import com.example.healthyapp.util.Constants.Companion.LOGIN_SCREEN
import com.example.healthyapp.util.Constants.Companion.PROFILE_SCREEN
import com.example.healthyapp.util.Constants.Companion.RECOMMENDATION_SCREEN
import com.example.healthyapp.util.Constants.Companion.REGISTER_SCREEN
import com.example.healthyapp.util.Constants.Companion.VERIFY_EMAIL_SCREEN

sealed class Screen(val route: String) {
    object LoginScreen: Screen(LOGIN_SCREEN)
    object RegisterScreen: Screen(REGISTER_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
    object HomeScreen: Screen(HOME_SCREEN)
    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    object RecommendationScreen: Screen(RECOMMENDATION_SCREEN)



}