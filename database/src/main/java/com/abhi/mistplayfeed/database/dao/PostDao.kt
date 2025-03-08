package com.abhi.mistplayfeed.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.abhi.mistplayfeed.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PostDao {

    @Query("SELECT * FROM post WHERE userId = :userId ORDER BY id")
    abstract fun getPostsForUser(userId: Long): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun savePosts(posts: List<PostEntity>)

    @Query("DELETE FROM post")
    abstract suspend fun deletePosts()

    @Transaction
    open suspend fun syncPosts(posts: List<PostEntity>) {
        deletePosts()
        savePosts(posts)
    }
}
