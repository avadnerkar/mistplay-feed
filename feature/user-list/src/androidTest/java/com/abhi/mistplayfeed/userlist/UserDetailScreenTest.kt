package com.abhi.mistplayfeed.userlist

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test
import com.abhi.mistplayfeed.ui.R as uiR

class UserDetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun should_show_loading_indicator_when_loading() {
        composeTestRule.setContent {
            UserListScreen(
                uiState = UserListState.Loading,
                onShowSnackbar = { _, _ -> false },
                onNavigateToDetail = {},
                onRetry = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.resources.getString(uiR.string.ui_loading_indicator_content_description))
            .assertExists()
    }
}
