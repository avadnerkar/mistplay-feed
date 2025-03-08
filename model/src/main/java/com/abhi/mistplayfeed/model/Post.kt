package com.abhi.mistplayfeed.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Long,
    val userId: Long,
    val title: String?,
    val body: String?
)
