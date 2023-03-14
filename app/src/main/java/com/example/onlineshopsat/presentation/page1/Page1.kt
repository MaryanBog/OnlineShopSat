package com.example.onlineshopsat.presentation.page1

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopsat.R
import com.example.onlineshopsat.navigation.NavigationTree
import com.example.onlineshopsat.presentation.profile.ProfileScreen
import com.example.onlineshopsat.presentation.home.HomeScreen
import com.example.onlineshopsat.ui.theme.BackgroundColor
import com.example.onlineshopsat.ui.theme.IconBackgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Page1(
    navController: NavController
) {

    val bottomNavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = NavigationTree.Home.name,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_home)
                    ),
                    BottomNavItem(
                        name = "Love",
                        route = NavigationTree.Love.name,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_love),
                    ),
                    BottomNavItem(
                        name = "Cart",
                        route = NavigationTree.Cart.name,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_cart)
                    ),
                    BottomNavItem(
                        name = "Chat",
                        route = NavigationTree.Chat.name,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_chat)
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = NavigationTree.Profile.name,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_profile)
                    ),
                ),
                bottomNavController = bottomNavHostController,
                onItemClick = {
                    bottomNavHostController.navigate(it.route)
                }
            )
        }
    ) {
        NavigationBottom(
            bottomNavHostController = bottomNavHostController,
            navController = navController
        )
    }
}

@Composable
fun NavigationBottom(
    bottomNavHostController: NavHostController,
    navController: NavController
) {
    NavHost(
        navController = bottomNavHostController,
        startDestination = NavigationTree.Home.name
    ) {
        composable(NavigationTree.Home.name) {
            HomeScreen(navController)
        }
        composable(NavigationTree.Love.name) {
            LoveScreen()
        }
        composable(NavigationTree.Cart.name) {
            CartScreen()
        }
        composable(NavigationTree.Chat.name) {
            ChatScreen()
        }
        composable(NavigationTree.Profile.name) {
            ProfileScreen(
                navController = navController
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    bottomNavController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    BottomNavigation(
        modifier = modifier
            .clip(
            RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ),
        backgroundColor = BackgroundColor,
        elevation = 5.dp
    ) {
        val backStackEntry = bottomNavController.currentBackStackEntryAsState()
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            val tint = if (selected) IconBackgroundColor else BackgroundColor

            BottomNavigationItem(
                selected = selected,
                selectedContentColor = BackgroundColor,
                unselectedContentColor = BackgroundColor,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = tint)
                            .padding(12.dp)
                    )
                }
            )
        }
    }
}

@Composable
fun LoveScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Love Screen")
    }
}

@Composable
fun CartScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Cart Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen")
    }
}