package com.abhi.mistplayfeed.userlist

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abhi.mistplayfeed.ui.animation.LocalNavAnimatedVisibilityScope
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute

fun NavGraphBuilder.userListScreen(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onNavigateToDetail: (userId: Long) -> Unit
) {
    composable<UserListRoute> {
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this) {
            UserListRoute(onShowSnackbar = onShowSnackbar, onNavigateToDetail = onNavigateToDetail)
        }
    }
}
