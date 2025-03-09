package com.abhi.mistplayfeed.userlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute

fun NavGraphBuilder.userListScreen(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onNavigateToDetail: (userId: Long) -> Unit
) {
    composable<UserListRoute> {
        UserListRoute(onShowSnackbar = onShowSnackbar, onNavigateToDetail = onNavigateToDetail)
    }
}
