package com.abhi.mistplayfeed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.abhi.mistplayfeed.userlist.UserListRoute
import com.abhi.mistplayfeed.userlist.userListScreen

@Composable
fun MFNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UserListRoute,
        modifier = modifier
    ) {
        userListScreen()
    }
}
