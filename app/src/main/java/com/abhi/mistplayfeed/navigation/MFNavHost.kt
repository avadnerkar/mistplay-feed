package com.abhi.mistplayfeed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.abhi.mistplayfeed.userdetail.navigateToUserDetail
import com.abhi.mistplayfeed.userdetail.userDetailScreen
import com.abhi.mistplayfeed.userlist.UserListRoute
import com.abhi.mistplayfeed.userlist.userListScreen

@Composable
fun MFNavHost(
    navController: NavHostController,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = UserListRoute,
        modifier = modifier
    ) {
        userListScreen(
            onShowSnackbar = onShowSnackbar,
            onNavigateToDetail = { navController.navigateToUserDetail(it) })
        userDetailScreen()
    }
}
