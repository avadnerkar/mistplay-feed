package com.abhi.mistplayfeed.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhi.mistplayfeed.model.Post

@Entity(
    tableName = "post"
)
data class PostEntity(
    @PrimaryKey
    val id: Long,
    val userId: Long,
    val title: String?,
    val body: String?
)

fun PostEntity.asPost() = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)

fun Post.asPostEntity() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body
)