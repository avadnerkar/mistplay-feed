package com.abhi.mistplayfeed.model

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String,
    val lng: String
)

@Serializable
data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: Geo?
)

@Serializable
data class Company(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)


@Serializable
data class User(
    val id: Long,
    val name: String,
    val username: String?,
    val address: Address?,
    val phone: String?,
    val website: String?,
    val company: Company?
)
