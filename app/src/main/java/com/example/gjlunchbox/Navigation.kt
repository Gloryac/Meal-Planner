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
import com.example.gjlunchbox.Registration.Login.LoginScreen
import com.example.gjlunchbox.Registration.SignUp.PrivacyPolicy
import com.example.gjlunchbox.Registration.SignUp.SignUpScreen
import com.example.gjlunchbox.Registration.SignUp.TermsOfServices

sealed class Route {
    data class LoginScreen(val name: String = "Login") : Route()
    data class SignUpScreen(val name: String = "SignUp") : Route()
    data class PrivacyPolicy(val name: String = "Privacy ]Policy") : Route()
    data class TermsOfServices(val name: String = "Terms of Services") : Route()
    data class HomeScreen(val name: String = "Home") : Route()
}



@Composable
fun AppNavigation(navHostController: NavHostController) {
    val currentUser = AuthManager.getCurrentUser()

    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            // Navigate to home if user is signed in
            navHostController.navigate(Route.HomeScreen().name) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        }
    }
    NavHost(
        navController = navHostController,
        startDestination = if (currentUser != null) Route.HomeScreen().name else "login_flow",
    ) {
        navigation(startDestination = Route.LoginScreen().name, route = "login_flow") {
            composable(route = Route.LoginScreen().name) {
                LoginScreen(
                    onLoginClick = {
                        navHostController.navigate(
                            Route.HomeScreen().name
                        ) {
                            popUpTo(route = "login_flow")
                        }
                    },
                    onSignUpClick = {
                        navHostController.navigateToSingleTop(
                            Route.SignUpScreen().name
                        )
                    }
                )
            }
            composable(route = Route.SignUpScreen().name) {
                SignUpScreen(
                    onSignUpClick = {
                        navHostController.navigateToSingleTop(
                            Route.HomeScreen().name
                        )
                    },
                    onLoginClick = {
                        navHostController.navigateToSingleTop(
                            Route.LoginScreen().name
                        )
                    },
                    onPrivacyPolicyClick = {
                        navHostController.navigate(
                            Route.PrivacyPolicy().name
                        ){
                            launchSingleTop = true
                        }
                    },
                    onTermsOfServicesClick = {
                        navHostController.navigate(
                            Route.TermsOfServices().name
                        ){
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(route = Route.TermsOfServices().name) {
                TermsOfServices {
                    navHostController.navigateUp()
                }
            }
            composable(route = Route.PrivacyPolicy().name) {
               PrivacyPolicy {
                    navHostController.navigateUp()

                }
            }
        }
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