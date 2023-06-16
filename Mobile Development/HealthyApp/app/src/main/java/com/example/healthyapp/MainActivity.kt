package com.example.healthyapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.healthyapp.navigation.NavGraph
import com.example.healthyapp.navigation.Screen
import com.example.healthyapp.ui.theme.HealthyAppTheme
import com.example.healthyapp.util.Constants.Companion.TAG
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val testingViewModel:TestingViewModel by viewModels()
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var animatedNavHostController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val testData = testingViewModel.testData.value
            animatedNavHostController = rememberAnimatedNavController()

//            Log.d(TAG, "testData: ${testData}")
            HealthyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController = animatedNavHostController)
                }
            }

            AuthState()
        }
    }

    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        Log.d(TAG, "AuthState: $isUserSignedOut")
        if (isUserSignedOut) {
            NavigateToLogin()
        }
        else {
            if (viewModel.isEmailVerified) {
                NavigateToHome()
            } else {
                NavigateToVerify()
            }
        }  
    }

    @Composable
    private fun NavigateToLogin() = animatedNavHostController.navigate(Screen.LoginScreen.route) {
        popUpTo(animatedNavHostController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToHome() = animatedNavHostController.navigate(Screen.HomeScreen.route) {
        popUpTo(animatedNavHostController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToVerify() = animatedNavHostController.navigate(Screen.VerifyEmailScreen.route) {
        popUpTo(animatedNavHostController.graph.id) {
            inclusive = true
        }
    }
}