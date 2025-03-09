package com.abhi.mistplayfeed.userlist

import com.abhi.mistplayfeed.userlist.components.UserProps

sealed interface UserListState {
    data object Loading : UserListState

    data class Loaded(
        val userProps: List<UserProps>,
        val hasSyncError: Boolean
    ) : UserListState
}
