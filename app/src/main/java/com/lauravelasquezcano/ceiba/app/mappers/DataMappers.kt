package com.lauravelasquezcano.ceiba.app.mappers

import com.lauravelasquezcano.ceiba.domain.*
import com.lauravelasquezcano.ceiba.app.database.Post as DbPost
import com.lauravelasquezcano.ceiba.app.database.User as DbUser

fun User.toDbUser(): DbUser =
    DbUser(
        id.toInt(),
        name,
        userName,
        email,
        address.street,
        address.suite,
        address.city,
        address.zipcode,
        address.geo.lat,
        address.geo.long,
        phone,
        website,
        company.name,
        company.catchPhrase,
        company.bs
    )

fun DbUser.toUser(): User =
    User(
        id.toString(),
        name,
        userName,
        email,
        Address(
            street,
            suite,
            city,
            zipcode,
            Geo(
                lat,
                lon
            )
        ),
        phone,
        website,
        Company(
            companyName,
            catchPhrase,
            bs
        )
    )

fun Post.toDbPost(): DbPost =
    DbPost(
        id.toInt(),
        userId,
        title,
        body
    )

fun DbPost.toPost(): Post =
    Post(
        id.toString(),
        userId,
        title,
        body
    )
