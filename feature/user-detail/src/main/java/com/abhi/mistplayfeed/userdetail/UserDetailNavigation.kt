package com.abhi.mistplayfeed.userdetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailRoute(val userId: Long)

fun NavController.navigateToUserDetail(
    userId: Long,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = UserDetailRoute(userId)) {
        navOptions()
    }
}

fun NavGraphBuilder.userDetailScreen() {
    composable<UserDetailRoute> {
        UserDetailRoute()
    }
}
