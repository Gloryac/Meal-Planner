package com.example.gjlunchbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gjlunchbox.Home.HomeScreen
import com.example.gjlunchbox.Onboarding.OnboardingScreen
import com.example.gjlunchbox.Registration.Login.LoginScreen
import com.example.gjlunchbox.Registration.SignUp.PrivacyPolicy
import com.example.gjlunchbox.Registration.SignUp.SignUpScreen
import com.example.gjlunchbox.Registration.SignUp.TermsOfServices

sealed class Route {
    data class OnboardingScreen(val name: String = "Onboarding") : Route()
    data class LoginScreen(val name: String = "Login") : Route()
    data class SignUpScreen(val name: String = "SignUp") : Route()
    data class PrivacyPolicy(val name: String = "Privacy Policy") : Route()
    data class TermsOfServices(val name: String = "Terms of Services") : Route()
    data class HomeScreen(val name: String = "Home") : Route()
}



@Composable
fun AppNavigation(navHostController: NavHostController, startDestination: String) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        // Onboarding Flow
        navigation(startDestination = Route.OnboardingScreen().name, route = "onboarding_flow") {
            composable(route = Route.OnboardingScreen().name) {
                OnboardingScreen {
                    // Navigate to Sign Up screen after Onboarding
                    navHostController.navigate(Route.SignUpScreen().name) {
                        popUpTo(Route.OnboardingScreen().name) { inclusive = true }
                    }
                }
            }
        }

        // Sign Up Flow
        navigation(startDestination = Route.SignUpScreen().name, route = "sign_up_flow") {
            composable(route = Route.SignUpScreen().name) {
                SignUpScreen(
                    onSignUpClick = {
                        navHostController.navigate(Route.HomeScreen().name) {
                            popUpTo(Route.SignUpScreen().name) { inclusive = true }
                        }
                    },
                    onLoginClick = {
                        navHostController.navigateToSingleTop(Route.LoginScreen().name)
                    },
                    onPrivacyPolicyClick = {
                        navHostController.navigate(Route.PrivacyPolicy().name) {
                            launchSingleTop = true
                        }
                    },
                    onTermsOfServicesClick = {
                        navHostController.navigate(Route.TermsOfServices().name) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(route = Route.LoginScreen().name) {
                LoginScreen(
                    onLoginClick = {
                        navHostController.navigate(Route.HomeScreen().name) {
                            popUpTo(route = "sign_up_flow") { inclusive = true }
                        }
                    },
                    onSignUpClick = {
                        navHostController.navigateToSingleTop(Route.SignUpScreen().name)
                    }
                )
            }
        }

        // Privacy Policy and Terms of Services
        composable(route = Route.PrivacyPolicy().name) {
            PrivacyPolicy {
                navHostController.navigateUp()
            }
        }
        composable(route = Route.TermsOfServices().name) {
            TermsOfServices {
                navHostController.navigateUp()
            }
        }

        // Home Screen
        composable(route = Route.HomeScreen().name) {
            HomeScreen()
        }
    }
}


fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}