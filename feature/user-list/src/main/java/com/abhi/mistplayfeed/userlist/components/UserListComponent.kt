package com.abhi.mistplayfeed.userlist.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.theme.MFTheme
import com.abhi.mistplayfeed.userlist.PostProps
import com.abhi.mistplayfeed.userlist.R
import com.abhi.mistplayfeed.userlist.UserProps

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
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = stringResource(R.string.feature_user_list_avatar_content_description),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Text(
                userProps.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium
            )
        }

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

@Composable
fun PostsSection(postProps: List<PostProps>, hasMorePosts: Boolean, onSeeMore: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(R.string.feature_user_list_posts),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )

            if (hasMorePosts) {
                TextButton(onClick = onSeeMore) {
                    Text(
                        stringResource(R.string.feature_user_list_posts_see_all)
                    )
                }
            }
        }

        postProps.forEach {
            Post(it)
        }
    }
}

@Composable
fun Post(postProps: PostProps) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            postProps.title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        postProps.body?.let {
            Text(
                postProps.title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun previewUser(id: Long): UserProps = UserProps(
    id = id,
    name = "Homer Simpson",
    companyName = "Springfield Power Plant",
    highlightedPosts = listOf(
        previewPost(), previewPost()
    ),
    hasMorePosts = true
)

private fun previewPost(): PostProps = PostProps(
    title = "$20 can buy you many peanuts", body = "Money can be exchanged for goods and services"
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun UserListComponent_Preview() {
    MFTheme {
        Surface {
            UserListComponent(
                users = listOf(
                    previewUser(1), previewUser(2)
                ),
                onNavigateToDetail = {}
            )
        }
    }
}
