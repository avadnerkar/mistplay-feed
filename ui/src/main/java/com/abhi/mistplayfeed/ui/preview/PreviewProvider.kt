@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.abhi.mistplayfeed.ui.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.abhi.mistplayfeed.ui.animation.LocalNavAnimatedVisibilityScope
import com.abhi.mistplayfeed.ui.animation.LocalSharedTransitionScope
import com.abhi.mistplayfeed.ui.theme.MFTheme

@Composable
fun PreviewProvider(
    content: @Composable () -> Unit
) {
    MFTheme {
        Surface {
            SharedTransitionLayout {
                CompositionLocalProvider(LocalSharedTransitionScope provides this) {
                    AnimatedVisibility(visible = true) {
                        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this) {
                            content()
                        }
                    }
                }
            }
        }
    }
}