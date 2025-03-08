package com.abhi.mistplayfeed.userlist.domain

import com.abhi.mistplayfeed.testing.MainDispatcherRule
import com.abhi.mistplayfeed.testing.TestFeedRepository
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

class SyncUsersAndPostsUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val feedRepository = TestFeedRepository()

    val useCase = SyncUsersAndPostsUseCase(feedRepository)

    @Test
    fun should_emit_syncing_and_success_when_refreshed() = runTest {
        feedRepository.forceSyncFailure(false)
        val syncState = useCase()
        useCase.refresh()

        val result = syncState.take(2).toList()
        assertEquals(result, listOf(SyncState.SYNCING, SyncState.SUCCESS))
    }

    @Test
    fun should_emit_syncing_and_error_on_network_exception() = runTest {
        feedRepository.forceSyncFailure(true)
        val syncState = useCase()
        useCase.refresh()

        val result = syncState.take(2).toList()
        assertEquals(result, listOf(SyncState.SYNCING, SyncState.ERROR))
    }
}
