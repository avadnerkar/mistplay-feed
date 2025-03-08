package com.abhi.mistplayfeed.database.di

import com.abhi.mistplayfeed.database.MFDatabase
import com.abhi.mistplayfeed.database.dao.PostDao
import com.abhi.mistplayfeed.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providesUserDao(
        database: MFDatabase
    ): UserDao = database.userDao()

    @Provides
    fun providesPostDao(
        database: MFDatabase
    ): PostDao = database.postDao()
}
