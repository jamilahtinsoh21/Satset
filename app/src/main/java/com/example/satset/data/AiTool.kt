package com.example.satset.data

data class AiTool(
    val id: Int,
    val name: String,
    val category: String,
    val tags: List<String>,
    val shortDesc: String,
    val about: String,
    val bestFor: List<String>,
    val pros: List<String>,
    val limit: String,
    val url: String,
    val color: String,
    val baseRating: Double,
    val ratingCount: Int
)