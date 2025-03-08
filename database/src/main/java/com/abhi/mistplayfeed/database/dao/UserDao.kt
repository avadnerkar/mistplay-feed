package com.abhi.mistplayfeed.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Query("SELECT * FROM user ORDER BY name")
    abstract fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM user where id = :id")
    abstract fun getUserById(id: Long): Flow<User>

    @Query("SELECT * FROM user JOIN post ON user.id = post.userId")
    abstract fun getUsersWithPosts(): Flow<Map<User, List<Post>>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveUsers(users: List<User>)

    @Query("DELETE FROM user")
    abstract suspend fun deleteUsers()

    @Transaction
    open suspend fun syncUsers(users: List<User>) {
        deleteUsers()
        saveUsers(users)
    }
}
