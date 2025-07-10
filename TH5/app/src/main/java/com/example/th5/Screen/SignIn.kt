package com.example.th5.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.th5.R
import com.example.th5.googlesigin.GoogleSigInClient
import kotlinx.coroutines.launch

@Composable
fun SignIn(onSignSuccess :()-> Unit) {
    val context = LocalContext.current
    val googleSignInClient = remember { GoogleSigInClient(context) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "logo",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.padding(7.dp))

        Text(
            text = "UTH SmartTasks",
            color = Color(0xFF006EE9),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.padding(7.dp))

        Text(
            text = "A simple and efficient to-do app",
            fontSize = 14.sp,
            color = Color(0xFF006EE9),
            textAlign = TextAlign.Center,

            )
        Spacer(Modifier.padding(50.dp))

        Text(
            text = "Welcome\n" +
                    "Ready to explore? Log in to get started.",
            fontSize = 17.sp,
            textAlign = TextAlign.Center,

            )

        Spacer(Modifier.padding(7.dp))

        Button(
            onClick = {
                scope.launch {
                    val result = googleSignInClient.signIn()
                    if (result) {
                        onSignSuccess()
                    } else {
                        Toast.makeText(context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5EDFF))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign in with Google",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,

                )
        }
    }
}
