package com.abhi.mistplayfeed.userlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute

fun NavGraphBuilder.userListScreen() {
    composable<UserListRoute> {
        UserListScreen()
    }
}
