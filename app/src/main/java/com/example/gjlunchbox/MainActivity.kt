package com.example.gjlunchbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.gjlunchbox.ui.theme.GJLunchboxTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gjlunchbox.Onboarding.OnboardingScreen
import com.example.gjlunchbox.Onboarding.OnboardingUtils

class MainActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnboardingUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            GJLunchboxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialize the NavHostController
                    val navController = rememberNavController()
                    // Set up navigation with user state
                    CheckUserState(navController)
//
                }
            }
        }
    }
}
//
//@Composable
//private fun ShowOnboardingScreen(onFinished: () -> Unit) {
//    val scope = rememberCoroutineScope()
//    OnboardingScreen {
//        onFinished()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    GJLunchboxTheme {
//        // Preview the onboarding screen for now
//        ShowOnboardingScreen {}
//    }
//}
