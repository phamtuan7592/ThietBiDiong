package com.example.tuan4_th2.battap2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan4_th2.R

data class NavbarModel(
    val title: String,
    val desc : String,
)

val navbar = listOf(
    NavbarModel(
        "Forget Password?",
        "Enter your Email, we will send you a verification code."
    ),
    NavbarModel(
        "Verify Code",
        "Enter the the code \n" +
                "we just sent you on your registered Email"
    ),
    NavbarModel(
        "Create new password",
        "Your new password must be different form previously used password"
    ),
    NavbarModel(
        "Confirm",
        "We are here to help you!"
    )
)

@Composable
fun Navbar(navbar: NavbarModel){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
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
        Spacer(Modifier.padding(vertical = 10.dp))

        Text(
            text = "UTH SmartTasks",
            color = Color(0xFF006EE9),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.padding(vertical = 5.dp))
        Text(
            text = navbar.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.padding(vertical = 5.dp))

        Text(
            text = navbar.desc,
            fontSize = 17.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,

        )
    }
}