package com.abhi.mistplayfeed.userlist

import com.abhi.mistplayfeed.model.Post

data class PostProps(
    val title: String,
    val body: String?
)

data class UserProps(
    val id: Long,
    val name: String,
    val companyName: String?,
    val highlightedPosts: List<PostProps>,
    val hasMorePosts: Boolean
)

sealed interface UserListState {
    data object Loading : UserListState

    data class Loaded(
        val userProps: List<UserProps>,
        val hasSyncError: Boolean
    ) : UserListState
}
