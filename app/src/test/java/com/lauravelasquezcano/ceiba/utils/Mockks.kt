package com.lauravelasquezcano.ceiba.utils

import com.lauravelasquezcano.ceiba.domain.model.*
import com.lauravelasquezcano.ceiba.app.database.User as DbUser
import com.lauravelasquezcano.ceiba.app.database.Post as DbPost

val mockedUsersList = listOf(
    User(
        "1",
        "Leanne Graham",
        "Bret",
        "Sincere@april.biz",
        Address(
            "Kulas Light",
            "Apt. 556",
            "Gwenborough",
            "92998-3874",
            Geo(
                "-37.3159",
                "81.1496"
            )
        ),
        "1-770-736-8031 x56442",
        "hildegard.org",
        Company(
            "Romaguera-Crona",
            "Multi-layered client-server neural-net",
            "harness real-time e-markets"
        )
    )
)

val mockedUser = User(
    "1",
    "Leanne Graham",
    "Bret",
    "Sincere@april.biz",
    Address(
        "Kulas Light",
        "Apt. 556",
        "Gwenborough",
        "92998-3874",
        Geo(
            "-37.3159",
            "81.1496"
        )
    ),
    "1-770-736-8031 x56442",
    "hildegard.org",
    Company(
        "Romaguera-Crona",
        "Multi-layered client-server neural-net",
        "harness real-time e-markets"
    )
)

val mockedDbUserList = listOf(
    DbUser(
        1,
        "Leanne Graham",
        "Bret",
        "Sincere@april.biz",
        "Kulas Light",
        "Apt. 556",
        "Gwenborough",
        "92998-3874",
        "-37.3159",
        "81.1496",
        "1-770-736-8031 x56442",
        "hildegard.org",
        "Romaguera-Crona",
        "Multi-layered client-server neural-net",
        "harness real-time e-markets"
    )
)

val mockedDbUSer = DbUser(
    1,
    "Leanne Graham",
    "Bret",
    "Sincere@april.biz",
    "Kulas Light",
    "Apt. 556",
    "Gwenborough",
    "92998-3874",
    "-37.3159",
    "81.1496",
    "1-770-736-8031 x56442",
    "hildegard.org",
    "Romaguera-Crona",
    "Multi-layered client-server neural-net",
    "harness real-time e-markets"
)

val mockedPostsList = listOf(
    Post(
        "1",
        1,
        "et ea vero quia laudantium autem",
        "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\naccusamus in eum beatae sit\nvel qui neque voluptates ut commodi qui incidunt\nut animi commodi"
    )
)

val mockedDbPostList = listOf(
    DbPost(
        1,
        1,
        "et ea vero quia laudantium autem",
        "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\naccusamus in eum beatae sit\nvel qui neque voluptates ut commodi qui incidunt\nut animi commodi"
    )
)