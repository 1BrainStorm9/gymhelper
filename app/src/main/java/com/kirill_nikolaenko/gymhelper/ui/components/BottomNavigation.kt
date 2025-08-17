package com.kirill_nikolaenko.gymhelper.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kirill_nikolaenko.gymhelper.ui.navigation.bottomNavItems

@Composable
fun BottomNavigation(navController: NavHostController, currentRoute: String?) {
    NavigationBar(
        containerColor = Color(238, 238, 238, 255),
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(33, 150, 243, 255),
                    selectedTextColor = Color(33, 150, 243, 255),
                    unselectedIconColor = Color(45, 45, 45, 179),
                    unselectedTextColor = Color(45, 45, 45, 179),
                    indicatorColor = Color(210, 176, 176, 0)
                )
            )
        }
    }
}