@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.abhi.mistplayfeed.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.abhi.mistplayfeed.ui.animation.LocalSharedTransitionScope
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
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
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
    }
}
