package com.example.tuan4_th2.battap2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Verify(navController: NavController, email: String){
    val otpLength = 5
    val otpValues = remember { mutableStateListOf("", "", "", "", "") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Navbar(navbar = navbar[1])

        Spacer(Modifier.padding(15.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
           for(i in 0 until otpLength){
               OutlinedTextField(
                   value = otpValues[i],
                   onValueChange = {
                       if(it.length <= 1 && (it.isEmpty() || it[0].isDigit())){
                           otpValues[i] = it
                       }
                   },
                   modifier = Modifier
                       .weight(1f)
                       .aspectRatio(1f),
                   singleLine = true,
                   isError = isError && otpValues[i].isEmpty(),
                   textStyle = TextStyle(
                       fontSize = 24.sp,
                       textAlign = TextAlign.Center
                   )
               )
           }
        }
        if (isError) {
            Text(
                text = "OTP không đúng hoặc chưa nhập đủ.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.Start)
            )
        }
        Spacer(Modifier.padding(15.dp))

        Button(
            onClick = {
                isError = otpValues.any { it.isEmpty() }
                if(!isError){
                    val otp = otpValues.joinToString(separator = "")
                    if(otp == "12345") {
                        navController.navigate("password/${email}/${otp}")
                    }else{
                        isError = true
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
        ) {
            Text("Next")
        }
    }
}