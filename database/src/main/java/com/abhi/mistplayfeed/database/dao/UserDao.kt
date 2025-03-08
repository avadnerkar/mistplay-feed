package com.abhi.mistplayfeed.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.abhi.mistplayfeed.database.entity.PostEntity
import com.abhi.mistplayfeed.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Query("SELECT * FROM user ORDER BY name")
    abstract fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user where id = :id")
    abstract fun getUserById(id: Long): Flow<UserEntity>

    @Query("SELECT * FROM user JOIN post ON user.id = post.userId")
    abstract fun getUsersWithPosts(): Flow<Map<UserEntity, List<PostEntity>>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveUsers(users: List<UserEntity>)

    @Query("DELETE FROM user")
    abstract suspend fun deleteUsers()

    @Transaction
    open suspend fun syncUsers(users: List<UserEntity>) {
        deleteUsers()
        saveUsers(users)
    }
}
