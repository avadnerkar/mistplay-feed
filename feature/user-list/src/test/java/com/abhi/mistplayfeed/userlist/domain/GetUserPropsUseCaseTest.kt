package com.abhi.mistplayfeed.userlist.domain

import com.abhi.mistplayfeed.testing.MainDispatcherRule
import com.abhi.mistplayfeed.testing.TestFeedRepository
import com.abhi.mistplayfeed.testing.data.TestDataTransform
import com.abhi.mistplayfeed.testing.data.generatePostTestData
import com.abhi.mistplayfeed.testing.data.generateUserTestData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

class GetUserPropsUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val feedRepository = TestFeedRepository()

    private val useCase = GetUserPropsUseCase(feedRepository)

    @Test
    fun users_should_be_sorted_alphabetically_by_name() = runTest {
        val userProps = useCase()
        feedRepository.sendFeedResources(
            users = generateUserTestData(
                listOf(
                    TestDataTransform(1L, { it.copy(name = "C") }),
                    TestDataTransform(2L, { it.copy(name = "B") }),
                    TestDataTransform(3L, { it.copy(name = "G") })
                )
            ),
            posts = generatePostTestData(
                mapOf(
                    1L to emptyList(),
                    2L to emptyList(),
                    3L to emptyList()
                )
            )
        )

        assertEquals(
            userProps.first().map { it.user.name },
            listOf("B", "C", "G")
        )
    }

    @Test
    fun should_limit_posts_to_2_per_user() = runTest {
        val userProps = useCase()
        feedRepository.sendFeedResources(
            users = generateUserTestData(
                listOf(
                    TestDataTransform(1L)
                )
            ),
            posts = generatePostTestData(
                mapOf(
                    1L to listOf(
                        TestDataTransform(1L),
                        TestDataTransform(2L),
                        TestDataTransform(3L)
                    )
                )
            )
        )

        val result = userProps.first()
        assertEquals(
            result.first().highlightedPosts.size,
            2
        )

        assertEquals(
            result.first().hasMorePosts,
            true
        )
    }
}
