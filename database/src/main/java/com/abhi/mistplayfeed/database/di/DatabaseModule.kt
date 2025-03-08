package com.abhi.mistplayfeed.database.di

import android.content.Context
import androidx.room.Room
import com.abhi.mistplayfeed.database.MFDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesMFDatabase(
        @ApplicationContext context: Context
    ): MFDatabase = Room.databaseBuilder(
        context,
        MFDatabase::class.java,
        "mf-database",
    ).build()
}
