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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.truncate

@Composable
fun Password(navController: NavController,  email: String, otp: String){

    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var isSubmit by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Navbar(navbar = navbar[2])

        Spacer(Modifier.padding(15.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {password = it},
            placeholder = { Text("Password")},
            label = { Text("Password")},
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = repassword,
            onValueChange = {repassword = it},
            placeholder = { Text("Confirm Password")},
            label = { Text("Confirm Password")},
            shape = RoundedCornerShape(12.dp)
        )

        if (password != repassword && isSubmit ){
            Text(
                text = "Mật khẩu không khớp!",
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
                isSubmit =  password != repassword || password.isEmpty()
                if(!isSubmit){
                    navController.navigate("confirm/${email}/${otp}/${password}")
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