package com.example.gjlunchbox

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun CheckUserState(navController: NavHostController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val startDestination = if (currentUser == null) {
        Route.OnboardingScreen().name
    } else {
        Route.HomeScreen().name
    }
    AppNavigation(navController, startDestination)
}
