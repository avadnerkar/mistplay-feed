package com.abhi.mistplayfeed.userlist.domain

import com.abhi.mistplayfeed.data.repository.FeedRepository
import com.abhi.mistplayfeed.ui.component.PostProps
import com.abhi.mistplayfeed.userlist.components.UserProps
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
                        id = user.id,
                        name = user.name,
                        companyName = user.company?.name,
                        highlightedPosts = posts.mapNotNull {
                            it.title?.let { title ->
                                PostProps(
                                    id = it.id,
                                    title = title,
                                    body = it.body
                                )
                            }
                        }.take(MAX_POSTS),
                        hasMorePosts = posts.size > MAX_POSTS
                    )
                }.sortedBy { it.name }
            }
    }

    companion object {
        private const val MAX_POSTS = 2
    }
}
