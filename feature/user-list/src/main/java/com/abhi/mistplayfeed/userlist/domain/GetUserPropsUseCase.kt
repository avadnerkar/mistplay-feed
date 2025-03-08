package com.abhi.mistplayfeed.userlist.domain

import com.abhi.mistplayfeed.data.repository.FeedRepository
import com.abhi.mistplayfeed.userlist.UserProps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserPropsUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    operator fun invoke(): Flow<List<UserProps>> {
        return feedRepository.getUsersWithPosts()
            .map { usersWithPosts ->
                usersWithPosts.map { (user, posts) ->
                    UserProps(
                        user = user,
                        highlightedPosts = posts.take(MAX_POSTS),
                        hasMorePosts = posts.size > MAX_POSTS
                    )
                }.sortedBy { it.user.name }
            }
    }

    companion object {
        const val MAX_POSTS = 2
    }
}
