package com.abhi.mistplayfeed.userlist.domain

import android.util.Log
import com.abhi.mistplayfeed.data.repository.FeedRepository
import javax.inject.Inject

class SyncUsersAndPostsUseCase @Inject constructor(private val feedRepository: FeedRepository) {
    suspend operator fun invoke(): Result<Unit> {
        feedRepository.syncUsersAndPosts()
        return Result.success(Unit)
//        return runCatching {
//            feedRepository.syncUsersAndPosts()
//            Result.success(Unit)
//        }.getOrElse {
//            Result.failure(it)
//        }
    }
}
