@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.abhi.mistplayfeed.ui.component

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.R
import com.abhi.mistplayfeed.ui.animation.LocalNavAnimatedVisibilityScope
import com.abhi.mistplayfeed.ui.animation.LocalSharedTransitionScope
import com.abhi.mistplayfeed.ui.preview.PreviewProvider

@Composable
fun NameHeader(
    name: String,
    userId: Long
) {
    with(LocalSharedTransitionScope.current) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.sharedElement(
                sharedContentState = rememberSharedContentState(
                    key = "name-header-${userId}"
                ),
                animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 300)
                }
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = stringResource(R.string.ui_avatar_content_description),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Text(
                name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun NameHeader_Preview() {
    PreviewProvider {
        NameHeader("Homer Simpson", userId = 1L)
    }
}