package com.example.tuan4_th2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tuan4_th2.baitap1.Library
import com.example.tuan4_th2.baitap1.LibraryViewModel
import com.example.tuan4_th2.battap2.Confirm
import com.example.tuan4_th2.battap2.Email
import com.example.tuan4_th2.battap2.Password
import com.example.tuan4_th2.battap2.Verify
import com.example.tuan4_th2.ui.theme.Tuan4_th2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val navController = rememberNavController()
//            val viewModel: LibraryViewModel = viewModel()
//
//            Library(navController = navController, viewModel = viewModel)
            NavData()
        }
    }
}

//@Composable
//fun MyApp() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "startScreen") {
//        composable("startScreen") { StartScreen(navController) }
//        composable("pages") {Page(navController)}
//    }
//}

@Composable
fun NavData(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "email"){
        composable("email") { Email(navController)}
        composable("verify/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            Verify(navController, email)
        }

        composable("password/{email}/{otp}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            Password(navController, email, otp)
        }

        composable("confirm/{email}/{otp}/{password}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            Confirm(navController, email, otp, password)
        }

    }
}