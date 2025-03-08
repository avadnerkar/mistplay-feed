package com.abhi.mistplayfeed.data.repository

import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getUsersWithPosts(): Flow<Map<User, List<Post>>>
    fun getUserById(id: Long): Flow<User>
    fun getPostsForUser(userId: Long): Flow<List<Post>>
    suspend fun syncUsersAndPosts()
}