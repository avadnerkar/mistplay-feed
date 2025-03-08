package com.abhi.mistplayfeed.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abhi.mistplayfeed.ui.component.LoadingIndicator
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize()) {
        when (val state = uiState) {
            UserListState.Loading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            is UserListState.Success -> {
                LazyColumn {
                    items(state.userProps) { item ->
                        Text(item.user.name, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}
