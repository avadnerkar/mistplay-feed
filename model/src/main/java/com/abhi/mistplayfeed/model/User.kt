package com.abhi.mistplayfeed.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Geo(
    val lat: Float,
    val lng: Float
)

data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    @Embedded
    val geo: Geo?
)

data class Company(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)

@Entity(
    tableName = "user"
)
data class User(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val username: String?,
    @Embedded
    val address: Address?,
    val phone: String?,
    val website: String?,
    @Embedded
    val company: Company?
)
