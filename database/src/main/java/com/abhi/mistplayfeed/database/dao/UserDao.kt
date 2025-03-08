package com.abhi.mistplayfeed.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.abhi.mistplayfeed.model.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Query("SELECT * FROM user ORDER BY name")
    abstract fun getUsers(): Flow<List<User>>

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
