package com.abhi.mistplayfeed.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhi.mistplayfeed.database.dao.PostDao
import com.abhi.mistplayfeed.database.dao.UserDao
import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User

@Database(
    entities = [
        User::class,
        Post::class
    ],
    version = 1
)
internal abstract class MFDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}
