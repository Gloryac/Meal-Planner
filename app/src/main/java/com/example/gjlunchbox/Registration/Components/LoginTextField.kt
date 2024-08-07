package com.example.gjlunchbox.Registration.Components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange:(String) -> Unit,
    labelText:String,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    OutlinedTextField(
        modifier = modifier,
        value =value,
        onValueChange =onValueChange,
        label = {Text(labelText)},
        trailingIcon={if (trailingIcon !=null) Icon(imageVector = trailingIcon, null)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation =visualTransformation,
        shape = RoundedCornerShape(15)
    )


}

@Preview(showBackground = true)
@Composable
fun PrevTextField(){
    LoginTextField(value = "", onValueChange = {}, labelText ="Password" )
}
