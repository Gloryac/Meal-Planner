package com.example.gjlunchbox.Registration.SignUp

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.gjlunchbox.Registration.Components.HeaderText
import com.example.gjlunchbox.Registration.Components.LoginTextField
import com.example.gjlunchbox.Registration.Login.defaultPadding
import com.example.gjlunchbox.Registration.Login.itemSpacing
import com.example.gjlunchbox.ui.theme.GJLunchboxTheme

@Composable
fun SignUpScreen(
    onSignUpClick:()-> Unit,
    onLoginClick:()-> Unit,
    onPrivacyPolicyClick:()-> Unit,
    onTermsOfServicesClick:()-> Unit){
    val (name, setName) = rememberSaveable{ mutableStateOf("") }
    val (email, setEmail) = rememberSaveable{ mutableStateOf("") }
    val (password, setPassword) = rememberSaveable{ mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = rememberSaveable{ mutableStateOf("") }
    val (agree, onAgree) = rememberSaveable{ mutableStateOf(false) }
    val context = LocalContext.current
    var isPasswordSame by remember{ mutableStateOf(false)}
    val isFieldsNotEmpty = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && agree

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AnimatedVisibility(isPasswordSame) {
            Text("Password does not match!",color = MaterialTheme.colorScheme.error)
        }
        HeaderText(text = "Sign Up",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start))
        LoginTextField(
            value =name,
            onValueChange = setName,
            labelText = "Enter FullName",
            trailingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email)
        Spacer(Modifier.height(itemSpacing))
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
            keyboardType = KeyboardType.Password,)

        Spacer(Modifier.height(itemSpacing))
        LoginTextField(
            value = confirmPassword,
            onValueChange = setConfirmPassword,
            labelText = "Confirm Password",
            trailingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,)
        Spacer(Modifier.height(itemSpacing))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            val termsText = "Terms of Services"
            val privacyText = "Privacy Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)){
                    append("I Agree to")
                }
                append("")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)){
                    pushStringAnnotation(tag = termsText, termsText)
                    append(termsText)
                }
                append(" and ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)){
                    pushStringAnnotation(tag = privacyText, privacyText)
                    append(privacyText)
                }
            }


            Checkbox(agree , onAgree )
            ClickableText(annotatedString ){
                offset ->
                annotatedString.getStringAnnotations(offset,offset).forEach {
                    when(it.tag){
                        termsText->{ Toast.makeText(context,"Terms and Conditions", Toast.LENGTH_SHORT).show()
                        onTermsOfServicesClick()}
                        privacyText->{ Toast.makeText(context,"Privacy Policy", Toast.LENGTH_SHORT).show()
                        onPrivacyPolicyClick()}
                    }
                }
            }
        }
        Spacer(Modifier.height(itemSpacing))
        Button(
            onClick ={
            isPasswordSame = password != confirmPassword
            if(!isPasswordSame){
                onSignUpClick()}} ,
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsNotEmpty) {
            Text("Sign Up")
        }
        Spacer(Modifier.height(itemSpacing))
        val signText = " Sign In"
        val signInAnnotation = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)){
                append("Already have and account?")
            }
            append("")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)){
                pushStringAnnotation(tag= signText,signText)
                append(signText)
            }

        }
        ClickableText(
            signInAnnotation
        ){
            offset->
            signInAnnotation.getStringAnnotations(offset,offset). forEach{
                if(it.tag == signText){
                    Toast.makeText(context,"Sign in clicked", Toast.LENGTH_SHORT).show()
                    onLoginClick()
                }
            }
        }



    }
}
@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen(){
    GJLunchboxTheme {
        SignUpScreen({},{},{},{})
    }
}