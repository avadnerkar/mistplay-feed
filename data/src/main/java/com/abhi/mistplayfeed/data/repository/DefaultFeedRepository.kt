package com.abhi.mistplayfeed.data.repository

import com.abhi.mistplayfeed.data.network.FeedService
import com.abhi.mistplayfeed.database.dao.PostDao
import com.abhi.mistplayfeed.database.dao.UserDao
import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultFeedRepository @Inject constructor(
    private val userDao: UserDao,
    private val postDao: PostDao,
    private val feedService: FeedService
) : FeedRepository {
    override fun getUsersWithPosts(): Flow<Map<User, List<Post>>> {
        return userDao.getUsersWithPosts()
    }

    override fun getUserById(id: Long): Flow<User> {
        return userDao.getUserById(id)
    }

    override fun getPostsForUser(userId: Long): Flow<List<Post>> {
        return postDao.getPostsForUser(userId)
    }

    override suspend fun syncUsersAndPosts() {
        coroutineScope {
            launch {
                userDao.syncUsers(feedService.getUsers())
            }
            launch {
                postDao.syncPosts(feedService.getPosts())
            }
        }
    }
}
