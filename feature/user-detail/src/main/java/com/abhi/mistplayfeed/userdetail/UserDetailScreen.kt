package com.abhi.mistplayfeed.userdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.mistplayfeed.ui.component.LoadingIndicator
import com.abhi.mistplayfeed.userdetail.component.UserDetailComponent

@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize()) {
        when (val state = uiState) {
            UserDetailState.Loading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            is UserDetailState.Loaded -> {
                UserDetailComponent(state)
            }
        }
    }
}