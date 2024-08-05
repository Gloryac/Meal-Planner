package com.example.gjlunchbox.Registration.Login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gjlunchbox.AuthManager
import com.example.gjlunchbox.R
import com.example.gjlunchbox.Registration.Components.HeaderText
import com.example.gjlunchbox.Registration.Components.LoginTextField
import com.example.gjlunchbox.ui.theme.GJLunchboxTheme

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(onLoginClick:()-> Unit, onSignUpClick: () -> Unit){
    val (email, setEmail) = rememberSaveable{ mutableStateOf("")}
    val (password, setPassword) = rememberSaveable{ mutableStateOf("")}
    val (checked, onChecked) = rememberSaveable{ mutableStateOf(false)}
    val (message, setMessage) = remember { mutableStateOf("") }
   // val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val isFieldsEmpty = email.isNotEmpty() && password.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HeaderText(text = "Login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        LoginTextField(
            value =email,
            onValueChange = setEmail,
            labelText = "Enter Email",
            trailingIcon = Icons.Default.Email,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email)
        Spacer(Modifier.height(itemSpacing))
        LoginTextField(
            value =password,
            onValueChange = setPassword,
            labelText = "Password",
            trailingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation())
        Spacer(Modifier.height(itemSpacing))
        
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Checkbox(checked = checked, onCheckedChange = onChecked)
                Text(text = "Remember me")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Forgot Password?")
            }
        }
        
        Spacer(Modifier.height(itemSpacing))
        
        Button(
            //onClick = onLoginClick,
            onClick = {
                AuthManager.login(email, password) { result ->
                    setMessage(result)
                    if (result == "Sign In Successful") {
                        onLoginClick()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsEmpty
        ) {
            Text("Login")
        }
       Spacer(Modifier.height(itemSpacing))
        AlternativeLoginOption(onIconClick = {}, onSignUpClick = onSignUpClick)
        Spacer(Modifier.height(itemSpacing))
        Text(text = message)
    }
}
@Composable
fun AlternativeLoginOption(
    onIconClick:()-> Unit,
    onSignUpClick:()-> Unit,
    modifier: Modifier = Modifier
){
    val googleIcon = R.drawable.google_icon
   Column(
       modifier=modifier.fillMaxWidth(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Row (modifier = modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically){
           Text("Or Sign in with Google")
           Spacer(modifier = Modifier.width(8.dp))
           Icon(painter = painterResource(id = googleIcon),
               contentDescription = "Google",
               modifier = Modifier
                   .size(32.dp)
                   .clickable { onIconClick }
                   .clip(CircleShape)
           )
       }
       Row(
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text("Don't have an Account?")
           Spacer(Modifier.height(itemSpacing))
           TextButton(onClick = onSignUpClick) {
               Text("Sign Up")
           }
       }
   }

}

@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen(){
    GJLunchboxTheme {
        LoginScreen({},{})
    }
}