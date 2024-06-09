package com.example.tddKotlin.model

val suzume = Bird(
    name = "スズメ",
    height = 14.0f,
    season = Season.entries.toList(),
    imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEigFrGTrd_XfJZ4QgZqFdN_V2ycWLgU2BSSvSZk4rgGBK1cP5mm-6GK1XIOZUZ-uy6PBrg6QGAM-Fi7aK13A-t1E1_AbD3a0AOQUT5J6qaP14-7dFVqa_Fbit0k7E97nObf1id_CPtfJVM/s400/bird_suzume.png"
)

val tsubame = Bird(
    name = "ツバメ",
    height = 17.0f,
    season = listOf(Season.SPRING, Season.SUMMER),
    imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg233ItG3bNHvBmjPD7_271h2GLGux7wbSovIfq1rKjkjv_lgMOzTYGRN9OOQb_hgtNzLIj_Sf-h7cnWvUZKFNpwoiAX4XT75zhdhf2_RiuZ27Y5R0IXVtluHmTkITg7odeozIUFaq1dxw/s800/bird_tsubame.png"
)

val magamo = Bird(
    name = "マガモ",
    height = 59.0f,
    season = listOf(Season.WINTER),
    imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiEqNkbGIsTG4VPiKE8V7W3Nnz8ZQckZmQomYo8x-_VkMNOZEn16enP4GMdYv1aLP2TsWArxnql-40knrx8A2OQgiQuFZEjzz5gMftddGT-h8R5mZGZyyy5JnfApCCCkijM5hSXk_CakNk/s400/bird_magamo.png"
)
