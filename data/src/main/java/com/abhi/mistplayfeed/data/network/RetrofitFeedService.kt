package com.abhi.mistplayfeed.data.network

import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface FeedApi {
    @GET(value = "users")
    suspend fun getUsers(): List<User>

    @GET(value = "posts")
    suspend fun getPosts(): List<Post>
}

@Singleton
internal class RetrofitFeedService @Inject constructor(
    okhttpCallFactory: Call.Factory,
    json: Json
) : FeedService {
    private val networkApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .callFactory { okhttpCallFactory.newCall(it) }
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(FeedApi::class.java)

    override suspend fun getUsers(): List<User> {
        return networkApi.getUsers()
    }

    override suspend fun getPosts(): List<Post> {
        return networkApi.getPosts()
    }
}
