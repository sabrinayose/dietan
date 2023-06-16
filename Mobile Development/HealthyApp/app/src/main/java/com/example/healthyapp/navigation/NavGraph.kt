package com.example.healthyapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthyapp.components.AuthTopBar
import com.example.healthyapp.screens.forgot.ForgotPasswordScreen
import com.example.healthyapp.screens.home.HomeScreen
import com.example.healthyapp.screens.login.LoginScreen
import com.example.healthyapp.screens.profile.ProfileViewModel
import com.example.healthyapp.screens.recommendation.RecommendationScreen
import com.example.healthyapp.screens.register.RegisterScreen
import com.example.healthyapp.screens.verify.VerifyScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun NavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                navigateToRegisterScreen = {
                    navController.navigate(Screen.RegisterScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                navigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPasswordScreen.route)
                }
            )
        }
        composable(
            route = Screen.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen {
                navController.popBackStack()
            }
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(
                navigateToLoginScreen = {
                    navController.navigate(Screen.LoginScreen.route){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                }
            ) {
                navController.navigate(Screen.ForgotPasswordScreen.route)
            }
        }
        composable(
            route = Screen.HomeScreen.route
        ) {
            ScaffoldWithTopBar(title = "Home") {
                HomeScreen {
                    navController.navigate(Screen.RecommendationScreen.route)
                }
            }
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ScaffoldWithTopBar(title = "Profile") {
            }
        }
        composable(
            route = Screen.VerifyEmailScreen.route
        ) {
            ScaffoldWithTopBar(title = "Verify Email") {
                VerifyScreen(navigateToHomeScreen = {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                })
            }
        }
        composable(
            route = Screen.RecommendationScreen.route
        ) {
            ScaffoldWithTopBar(title = "Food Recommendations") {
                RecommendationScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(viewModel: ProfileViewModel = hiltViewModel(),
                       title: String,
                       content: @Composable () -> Unit){
    Scaffold(
        topBar = {
                 AuthTopBar(
                     title,
                     {
                            viewModel.signOut()
                     }, {
                            viewModel.revokeAccess()
                     }
                 )
        },
    ) { padding ->
        Box(
        modifier = Modifier.padding(padding))
        {
            content()
        }
    }
}