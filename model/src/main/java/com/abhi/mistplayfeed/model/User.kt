package com.abhi.mistplayfeed.model

data class Geo(
    val lat: Float,
    val lng: Float
)

data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: Geo?
)

data class Company(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)

data class User(
    val id: Long,
    val name: String?,
    val username: String?,
    val address: Address?,
    val phone: String?,
    val website: String?,
    val company: Company?
)
