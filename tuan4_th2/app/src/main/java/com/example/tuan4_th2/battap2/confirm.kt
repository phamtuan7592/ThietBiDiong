package com.example.tuan4_th2.battap2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController

@Composable
fun Confirm(navController: NavController, email: String, otp: String, password: String){

    var emailInput by remember { mutableStateOf(email) }
    var otpInput by remember { mutableStateOf(otp) }
    var passwordInput by remember { mutableStateOf(password) }

    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Navbar(navbar = navbar[3])

        Spacer(Modifier.padding(15.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            onValueChange = {
                emailInput = it
                isError = false
            },
            placeholder = { Text("Your Email") },
            label = { Text("Email") },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = otpInput,
            onValueChange = {
                otpInput = it
                isError = false
            },
            placeholder = { Text("Your OTP") },
            label = { Text("OTP") },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            onValueChange = {
                passwordInput = it
                isError = false
            },
            placeholder = { Text("Your Password") },
            label = { Text("Password") },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.padding(10.dp))

        if (isError) {
            Text(
                text = "Email, OTP hoặc Password không đúng!",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

        Button(
            onClick = {
                if (emailInput == email && otpInput == otp && passwordInput == password) {
                    navController.navigate("email")
                } else {
                    isError = true
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
        ) {
            Text("Submit")
        }
    }
}

