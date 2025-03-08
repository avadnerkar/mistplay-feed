package com.abhi.mistplayfeed.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun UserListScreen(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.refresh()
    }
    Column(modifier = modifier.fillMaxSize()) {
        when (val state = uiState) {
            UserListState.Loading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            is UserListState.Loaded -> {
                ErrorHandler(
                    hasError = state.hasSyncError,
                    onRetry = viewModel::refresh,
                    onShowSnackbar = onShowSnackbar
                )
                UserListComponent(users = state.userProps)
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
    val errorMessage = stringResource(R.string.mf_feature_user_list_network_error)
    val retry = stringResource(R.string.mf_feature_user_list_network_error_refresh)
    LaunchedEffect(key1 = hasError) {
        if (hasError) {
            val snackbarResult = onShowSnackbar(errorMessage, retry)
            if (snackbarResult) {
                onRetry()
            }
        }
    }

}
