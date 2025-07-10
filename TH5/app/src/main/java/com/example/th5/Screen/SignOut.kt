package com.example.th5.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.th5.R
import com.example.th5.googlesigin.GoogleSigInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun SignOut(onSignOut: () -> Unit) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val googleSignInClient = remember { GoogleSigInClient(context) }
    val user = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        user?.photoUrl?.let { photoUri ->
            Image(
                painter = rememberAsyncImagePainter(model = photoUri),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
        } ?: Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "Default Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )


        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = user?.displayName?: "No Name",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = user?.email ?: "No Email",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    googleSignInClient.signOut()
                    onSignOut()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Đăng xuất", fontSize = 16.sp, color = Color.White)
        }
    }
}

