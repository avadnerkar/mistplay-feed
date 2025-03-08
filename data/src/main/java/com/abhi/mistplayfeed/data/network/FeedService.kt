package com.abhi.mistplayfeed.data.network

import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User

interface FeedService {
    suspend fun getUsers(): List<User>
    suspend fun getPosts(): List<Post>
}
