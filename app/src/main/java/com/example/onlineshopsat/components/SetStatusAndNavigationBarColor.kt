package com.example.onlineshopsat.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusAndNavigationBarColor(
    statusBarColor: Color,
    navigationBarColor: Color,
    darkIcons: Boolean
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = darkIcons
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = darkIcons
        )
    }
}