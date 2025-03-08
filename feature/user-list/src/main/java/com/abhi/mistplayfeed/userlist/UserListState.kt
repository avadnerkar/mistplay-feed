package com.abhi.mistplayfeed.userlist

import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User

data class UserProps(
    val user: User,
    val highlightedPosts: List<Post>,
    val hasMorePosts: Boolean
)

sealed interface UserListState {
    data object Loading : UserListState

    data class Loaded(
        val userProps: List<UserProps>,
        val hasSyncError: Boolean
    ) : UserListState
}
