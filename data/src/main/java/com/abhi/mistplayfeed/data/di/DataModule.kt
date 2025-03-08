package com.abhi.mistplayfeed.data.di

import com.abhi.mistplayfeed.data.network.FeedService
import com.abhi.mistplayfeed.data.network.RetrofitFeedService
import com.abhi.mistplayfeed.data.repository.DefaultFeedRepository
import com.abhi.mistplayfeed.data.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsFeedRepository(
        feedRepository: DefaultFeedRepository
    ): FeedRepository

    @Binds
    internal abstract fun bindsFeedService(
        feedService: RetrofitFeedService
    ): FeedService
}