package com.abhi.mistplayfeed.userlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute

fun NavGraphBuilder.userListScreen(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
) {
    composable<UserListRoute> {
        UserListScreen(onShowSnackbar = onShowSnackbar, onNavigateToDetail = {})
    }
}
