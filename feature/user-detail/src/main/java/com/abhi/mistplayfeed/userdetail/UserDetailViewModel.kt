package com.abhi.mistplayfeed.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.mistplayfeed.userdetail.domain.GetUserDetailPropsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    getUserDetailProps: GetUserDetailPropsUseCase
) : ViewModel() {
    val uiState: StateFlow<UserDetailState> = getUserDetailProps(1L).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserDetailState.Loading
    )
}
