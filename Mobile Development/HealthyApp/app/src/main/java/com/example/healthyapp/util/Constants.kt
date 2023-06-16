package com.example.healthyapp.util

class Constants {
    companion object{
        const val BASE_URL = "https://dog.ceo/"
        const val RANDOM_URL = "api/breeds/image/random"
        const val TAG = "DEBUG_TAG"
        const val LOGIN_SCREEN = "LOGIN_SCREEN"
        const val REGISTER_SCREEN = "REGISTER_SCREEN"
        const val PROFILE_SCREEN = "PROFILE_SCREEN"
        const val HOME_SCREEN = "HOME_SCREEN"
        const val VERIFY_EMAIL_SCREEN = "VERIFY_EMAIL_SCREEN"
        const val FORGOT_PASSWORD_SCREEN = "FORGOT_PASSWORD_SCREEN"
        const val RECOMMENDATION_SCREEN = "RECOMMENDATION_SCREEN"

        // MESSAGES
        const val VERIFY_EMAIL_MESSAGE = "Verification email has been sent to your email !"
        const val SIGN_OUT_MESSAGE = "Sign Out"
        const val REVOKE_ACCESS_MESSAGE = "Revoke Account"
        const val ALREADY_VERIFIED = "Already verified?"
        const val SPAM_EMAIL = "If not, please also check the spam folder."
    }
}