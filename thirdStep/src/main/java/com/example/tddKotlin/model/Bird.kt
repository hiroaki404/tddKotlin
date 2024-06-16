package com.example.tddKotlin.model

data class Bird(
    val name: String,
    val height: Float,
    val seasons: List<Season>,
    val imageUrl: String
)

enum class Season(val value: String) {
    WINTER("冬"),
    SPRING("春"),
    SUMMER("夏"),
    FALL("秋")
}
