package com.abhi.mistplayfeed.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.R

@Composable
fun LoadingIndicator(modifier: Modifier, size: Dp = 48.dp) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val description = stringResource(R.string.ui_loading_indicator_content_description)
        CircularProgressIndicator(
            modifier = Modifier
                .size(size)
                .semantics { contentDescription = description },
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 4.dp,
        )
    }
}