@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.abhi.mistplayfeed.ui.animation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.compositionLocalOf

val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope> { throw Exception("Missing SharedTransitionScope provider") }
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope> { throw Exception("Missing AnimatedVisibilityScope provider") }
