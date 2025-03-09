package com.abhi.mistplayfeed.userlist.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.R
import com.abhi.mistplayfeed.ui.component.Post
import com.abhi.mistplayfeed.ui.component.PostProps
import com.abhi.mistplayfeed.ui.preview.PreviewProvider

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
                stringResource(R.string.ui_posts),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )

            if (hasMorePosts) {
                TextButton(onClick = onSeeMore) {
                    Text(
                        stringResource(R.string.ui_posts_see_all)
                    )
                }
            }
        }

        postProps.forEach {
            Post(it)
        }
    }
}

private fun previewPost(): PostProps = PostProps(
    id = 1L,
    title = "$20 can buy you many peanuts",
    body = "Money can be exchanged for goods and services"
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun PostSection_Preview() {
    PreviewProvider {
        PostsSection(
            postProps = listOf(
                previewPost(), previewPost()
            ),
            hasMorePosts = true,
            onSeeMore = {}
        )
    }
}

