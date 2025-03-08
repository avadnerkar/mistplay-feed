package com.abhi.mistplayfeed.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhi.mistplayfeed.database.dao.PostDao
import com.abhi.mistplayfeed.database.dao.UserDao
import com.abhi.mistplayfeed.database.entity.PostEntity
import com.abhi.mistplayfeed.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PostEntity::class
    ],
    version = 1
)
internal abstract class MFDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}
