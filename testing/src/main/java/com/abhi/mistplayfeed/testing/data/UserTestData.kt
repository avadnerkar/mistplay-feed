package com.abhi.mistplayfeed.testing.data

import com.abhi.mistplayfeed.model.Address
import com.abhi.mistplayfeed.model.Company
import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User

data class TestDataTransform<T>(val id: Long, val transform: (item: T) -> T = { it })

fun generateUserTestData(
    users: List<TestDataTransform<User>>,
): List<User> {
    return users.map {
        it.transform(
            User(
                name = "Homer Simpson",
                id = it.id,
                username = "av",
                address = Address(
                    street = "742 Evergreen Terrace",
                    suite = "1",
                    city = "Springfield",
                    zipcode = "90210",
                    geo = null
                ),
                phone = "514-111-1111",
                website = "https://example.com",
                company = Company(
                    name = "Nuclear power plant",
                    catchPhrase = "Doh",
                    bs = "BS?"
                ),
            )
        )
    }
}

fun generatePostTestData(
    userIdToPosts: Map<Long, List<TestDataTransform<Post>>>
): List<Post> {
    return userIdToPosts.entries.flatMap { (userId, posts) ->
        posts.map { post ->
            post.transform(
                Post(
                    id = post.id,
                    userId = userId,
                    title = "$20 can buy you many peanuts",
                    body = "Money can be exchanged for goods and services"
                )
            )
        }
    }
}