package com.abhi.mistplayfeed.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhi.mistplayfeed.model.Address
import com.abhi.mistplayfeed.model.Company
import com.abhi.mistplayfeed.model.Geo
import com.abhi.mistplayfeed.model.User

data class GeoEntity(
    val lat: String,
    val lng: String
)

data class AddressEntity(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    @Embedded(prefix = "geo_")
    val geo: GeoEntity?
)

data class CompanyEntity(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)

@Entity(
    tableName = "user"
)
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val username: String?,
    @Embedded(prefix = "address_")
    val address: AddressEntity?,
    val phone: String?,
    val website: String?,
    @Embedded(prefix = "company_")
    val company: CompanyEntity?
)

fun UserEntity.asUser() = User(
    id = id,
    name = name,
    username = username,
    address = address?.run {
        Address(
            street = street,
            suite = suite,
            city = city,
            zipcode = zipcode,
            geo = geo?.run {
                Geo(
                    lat = lat,
                    lng = lng
                )
            }
        )
    },
    phone = phone,
    website = website,
    company = company?.run {
        Company(
            name = name,
            catchPhrase = catchPhrase,
            bs = bs
        )
    }
)

fun User.asUserEntity() = UserEntity(
    id = id,
    name = name,
    username = username,
    address = address?.run {
        AddressEntity(
            street = street,
            suite = suite,
            city = city,
            zipcode = zipcode,
            geo = geo?.run {
                GeoEntity(
                    lat = lat,
                    lng = lng
                )
            }
        )
    },
    phone = phone,
    website = website,
    company = company?.run {
        CompanyEntity(
            name = name,
            catchPhrase = catchPhrase,
            bs = bs
        )
    }
)
