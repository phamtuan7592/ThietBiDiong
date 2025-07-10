package com.example.tuan4_th2.battap2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun Email(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Navbar(navbar = navbar[0])

        Spacer(Modifier.padding(15.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = { Text("Your Email")},
            label = { Text("Email") },
            isError = isEmailError,
            shape = RoundedCornerShape(12.dp)
        )

        if (isEmailError) {
            Text(
                text = "Email không hợp lệ.",
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
                isEmailError = !email.endsWith("@gmail.com") || email.isEmpty()
                if(!isEmailError){
                    navController.navigate("verify/${email}")
                }
            },
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
        ) {
            Text("Next")
        }
    }
}
