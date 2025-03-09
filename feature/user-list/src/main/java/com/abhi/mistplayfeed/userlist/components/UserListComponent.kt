@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.abhi.mistplayfeed.userlist.components

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.component.NameHeader
import com.abhi.mistplayfeed.ui.component.PostProps
import com.abhi.mistplayfeed.ui.preview.PreviewProvider
import com.abhi.mistplayfeed.userlist.R

data class UserProps(
    val id: Long,
    val name: String,
    val companyName: String?,
    val highlightedPosts: List<PostProps>,
    val hasMorePosts: Boolean
)

@Composable
fun UserListComponent(
    users: List<UserProps>,
    onNavigateToDetail: (userId: Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(users, key = { it.id }) { item ->
            UserCard(item, onNavigateToDetail = {
                onNavigateToDetail(item.id)
            })
        }
    }
}

@Composable
fun UserCard(userProps: UserProps, onNavigateToDetail: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onNavigateToDetail() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        NameHeader(
            name = userProps.name,
            userId = userProps.id
        )

        userProps.companyName?.let {
            Text(
                stringResource(R.string.feature_user_list_works_at, it),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        userProps.highlightedPosts.takeUnless { it.isEmpty() }?.let {
            PostsSection(postProps = it, hasMorePosts = userProps.hasMorePosts, onSeeMore = {
                onNavigateToDetail()
            })
        }
    }
}

private fun previewUser(id: Long): UserProps = UserProps(
    id = id,
    name = "Homer Simpson",
    companyName = "Springfield Power Plant",
    highlightedPosts = listOf(
        previewPost(1), previewPost(2)
    ),
    hasMorePosts = true
)

private fun previewPost(id: Long): PostProps = PostProps(
    id = id,
    title = "$20 can buy you many peanuts",
    body = "Money can be exchanged for goods and services"
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun UserListComponent_Preview() {
    PreviewProvider {
        UserListComponent(
            users = listOf(
                previewUser(1), previewUser(2)
            ),
            onNavigateToDetail = {}
        )
    }
}
