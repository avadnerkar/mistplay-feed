package com.abhi.mistplayfeed.userdetail.domain

import com.abhi.mistplayfeed.data.repository.FeedRepository
import com.abhi.mistplayfeed.ui.component.PostProps
import com.abhi.mistplayfeed.userdetail.UserDetailState
import com.abhi.mistplayfeed.userdetail.component.CompanyProps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserDetailPropsUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    operator fun invoke(userId: Long): Flow<UserDetailState> {
        return combine(
            feedRepository.getUserById(userId),
            feedRepository.getPostsForUser(userId)
        ) { user, posts ->
            UserDetailState.Loaded(
                userId = userId,
                name = user.name,
                companyProps = user.company?.let {
                    val companyName = it.name
                    if (companyName != null) {
                        CompanyProps(
                            companyName = companyName,
                            catchPhrase = it.catchPhrase
                        )
                    } else {
                        null
                    }
                },
                posts = posts.mapNotNull {
                    it.title?.let { title ->
                        PostProps(
                            id = it.id,
                            title = title,
                            body = it.body
                        )
                    }
                }
            )
        }
    }
}