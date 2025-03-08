package com.abhi.mistplayfeed.testing

import com.abhi.mistplayfeed.data.repository.FeedRepository
import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class TestFeedRepository : FeedRepository {
    private val userResources: MutableSharedFlow<List<User>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val postResources: MutableSharedFlow<List<Post>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private var forceSyncFailure = false

    override fun getUsersWithPosts(): Flow<Map<User, List<Post>>> = combine(
        userResources,
        postResources
    ) { users, posts ->
        users.fold(mutableMapOf()) { acc, user ->
            acc[user] = posts.filter { it.userId == user.id }
            return@fold acc
        }
    }

    override fun getUserById(id: Long): Flow<User> = userResources
        .map {
            it.find { userId -> userId.id == id }
        }.filterNotNull()

    override fun getPostsForUser(userId: Long): Flow<List<Post>> = postResources
        .map {
            it.filter { post -> post.userId == userId }
        }

    override suspend fun syncUsersAndPosts() {
        if (forceSyncFailure) {
            throw Exception("Unable to sync users and posts")
        }
    }

    //Functions to facilitate testing:
    fun sendFeedResources(users: List<User>, posts: List<Post>) {
        userResources.tryEmit(users)
        postResources.tryEmit(posts)
    }

    fun forceSyncFailure(shouldFail: Boolean) {
        forceSyncFailure = shouldFail
    }
}
