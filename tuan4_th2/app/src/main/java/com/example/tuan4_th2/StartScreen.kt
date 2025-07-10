package com.example.tuan4_th2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun StartScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Box(modifier = Modifier
            .clickable { navController.navigate("pages") }
        ){
            Text(
                text = "UTH SmartTasks",
                color = Color(0xFF006EE9),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

