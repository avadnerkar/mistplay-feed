package com.abhi.mistplayfeed.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "post"
)
data class Post(
    @PrimaryKey
    val id: Long,
    val userId: Long,
    val title: String?,
    val body: String?
)
