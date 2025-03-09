package com.abhi.mistplayfeed.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.mistplayfeed.ui.component.LoadingIndicator
import com.abhi.mistplayfeed.userlist.components.UserListComponent

@Composable
internal fun UserListRoute(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = hiltViewModel(),
    onNavigateToDetail: (userId: Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.refresh()
    }
    UserListScreen(
        uiState = uiState,
        onShowSnackbar = onShowSnackbar,
        modifier = modifier,
        onNavigateToDetail = onNavigateToDetail,
        onRetry = viewModel::refresh
    )
}

@Composable
fun UserListScreen(
    uiState: UserListState,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (userId: Long) -> Unit,
    onRetry: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        when (val state = uiState) {
            UserListState.Loading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            is UserListState.Loaded -> {
                ErrorHandler(
                    hasError = state.hasSyncError,
                    onRetry = onRetry,
                    onShowSnackbar = onShowSnackbar
                )
                UserListComponent(users = state.userProps, onNavigateToDetail = onNavigateToDetail)
            }
        }
    }
}

@Composable
fun ErrorHandler(
    hasError: Boolean,
    onRetry: () -> Unit,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
) {
    val errorMessage = stringResource(R.string.feature_user_list_network_error)
    val retry = stringResource(R.string.feature_user_list_network_error_refresh)
    LaunchedEffect(key1 = hasError) {
        if (hasError) {
            val snackbarResult = onShowSnackbar(errorMessage, retry)
            if (snackbarResult) {
                onRetry()
            }
        }
    }

}
