package com.abhi.mistplayfeed.userlist.domain

import com.abhi.mistplayfeed.data.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

enum class SyncState {
    SYNCING, SUCCESS, ERROR
}

class SyncUsersAndPostsUseCase @Inject constructor(private val feedRepository: FeedRepository) {
    private val trigger = MutableSharedFlow<Unit>(replay = 1)
    operator fun invoke(): Flow<SyncState> {
        return trigger.flatMapLatest {
            flow {
                emit(SyncState.SYNCING)
                emit(
                    runCatching {
                        feedRepository.syncUsersAndPosts()
                        SyncState.SUCCESS
                    }.getOrElse { SyncState.ERROR }
                )
            }
        }
    }

    fun refresh() {
        trigger.tryEmit(Unit)
    }
}
