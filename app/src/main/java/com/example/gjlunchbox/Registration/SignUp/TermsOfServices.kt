package com.example.gjlunchbox.Registration.SignUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TermsOfServices(onBtnClick:() -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("Terms of Services")
        Button(onClick = onBtnClick) {
            Text("Finish")

        }
    }
}