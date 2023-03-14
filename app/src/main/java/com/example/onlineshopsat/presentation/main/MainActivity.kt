package com.example.onlineshopsat.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.onlineshopsat.navigation.Navigation
import com.example.onlineshopsat.ui.theme.OnlineShopSatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlineShopSatTheme(
                dynamicColor = false,
                darkTheme = true
            ) {
                Navigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnlineShopSatTheme {

    }
}