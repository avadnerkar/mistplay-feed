package com.abhi.mistplayfeed.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.mistplayfeed.userlist.domain.GetUserPropsUseCase
import com.abhi.mistplayfeed.userlist.domain.SyncUsersAndPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    getUserProps: GetUserPropsUseCase,
    syncUsersAndPostsUseCase: SyncUsersAndPostsUseCase
) : ViewModel() {
    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    val uiState: StateFlow<UserListState> = getUserProps()
        .map {
            UserListState.Success(userProps = it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UserListState.Loading
        )

    init {
        viewModelScope.launch {
            _error.value = syncUsersAndPostsUseCase().exceptionOrNull()
        }
    }
}
