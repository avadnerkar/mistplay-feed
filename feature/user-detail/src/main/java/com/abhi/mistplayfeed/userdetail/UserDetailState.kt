package com.abhi.mistplayfeed.userdetail

import com.abhi.mistplayfeed.ui.component.PostProps
import com.abhi.mistplayfeed.userdetail.component.CompanyProps

sealed interface UserDetailState {
    data object Loading : UserDetailState
    data class Loaded(
        val name: String, val companyProps: CompanyProps?, val posts: List<PostProps>
    ) : UserDetailState
}
