package com.abhi.mistplayfeed.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.mistplayfeed.userlist.domain.GetUserPropsUseCase
import com.abhi.mistplayfeed.userlist.domain.SyncState
import com.abhi.mistplayfeed.userlist.domain.SyncUsersAndPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    getUserProps: GetUserPropsUseCase,
    private val syncUsersAndPosts: SyncUsersAndPostsUseCase
) : ViewModel() {
    val uiState: StateFlow<UserListState> = combine(
        getUserProps(),
        syncUsersAndPosts()
    ) { userProps, syncState ->
        if (userProps.isEmpty() && syncState == SyncState.SYNCING) {
            //Database is empty but network is still loading:
            UserListState.Loading
        } else {
            UserListState.Loaded(
                userProps = userProps,
                hasSyncError = syncState == SyncState.ERROR
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserListState.Loading
    )

    fun refresh() {
        syncUsersAndPosts.refresh()
    }
}
