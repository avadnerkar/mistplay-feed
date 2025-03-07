package com.abhi.mistplayfeed.ui.component

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.theme.MFTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    @StringRes titleRes: Int,
    actionIcon: ImageVector?,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionClick: () -> Unit = {},
) {
    Surface(shadowElevation = 3.dp) {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(id = titleRes)) },
            actions = {
                actionIcon?.let {
                    IconButton(onClick = onActionClick) {
                        Icon(
                            imageVector = it,
                            contentDescription = actionIconContentDescription,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            colors = colors,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun MollrTopBarPreview() {
    MFTheme {
        TopBar(
            titleRes = android.R.string.untitled,
            actionIcon = null,
            actionIconContentDescription = "Action icon",
        )
    }
}
