@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.abhi.mistplayfeed.ui.component

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.animation.LocalNavAnimatedVisibilityScope
import com.abhi.mistplayfeed.ui.animation.LocalSharedTransitionScope
import com.abhi.mistplayfeed.ui.preview.PreviewProvider

data class PostProps(
    val id: Long, val title: String, val body: String?
)

@Composable
fun Post(postProps: PostProps) {
    with(LocalSharedTransitionScope.current) {
        Column(
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = "post-${postProps.id}"
                    ),
                    animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 300)
                    }
                )
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
                    postProps.body,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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
        Post(
            postProps = previewPost()
        )
    }
}
