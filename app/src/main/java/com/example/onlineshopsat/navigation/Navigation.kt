package com.example.onlineshopsat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopsat.presentation.login.LoginScreen
import com.example.onlineshopsat.presentation.page1.Page1
import com.example.onlineshopsat.presentation.sign.SignInScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Sign_In.name){
        composable(NavigationTree.Sign_In.name){
            SignInScreen(navController = navController)
        }
        composable(NavigationTree.Login.name){
            LoginScreen(navController = navController)
        }
        composable(NavigationTree.Page_1.name){
            Page1(navController = navController)
        }
    }
}