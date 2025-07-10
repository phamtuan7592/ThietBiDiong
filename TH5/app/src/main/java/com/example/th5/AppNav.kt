// AppNavigation.kt
package com.example.th5

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.th5.Screen.SignIn
import com.example.th5.Screen.SignOut

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signin") {
        composable("signin") {
            SignIn(onSignSuccess = {
                navController.navigate("signout") {
                    popUpTo("signin") { inclusive = true }
                }
            })
        }
        composable("signout") {
            SignOut(onSignOut = {
                navController.navigate("signin") {
                    popUpTo("signout") { inclusive = true }
                }
            })
        }
    }
}
