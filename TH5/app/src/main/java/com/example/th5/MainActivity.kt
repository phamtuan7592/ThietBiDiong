package com.example.th5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.th5.Screen.SignOut
import com.example.th5.Screen.SignIn
import com.example.th5.googlesigin.GoogleSigInClient
import com.example.th5.ui.theme.TH5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val googleAuthClient = GoogleSigInClient(applicationContext)
            TH5Theme {
                var isSignedIn by rememberSaveable { mutableStateOf(googleAuthClient.isSignIn()) }

                if (isSignedIn) {
                    SignOut(
                        onSignOut = { isSignedIn = false }
                    )
                } else {
                    SignIn(
                        onSignSuccess = { isSignedIn = true }
                    )
                }
            }
        }
    }
}