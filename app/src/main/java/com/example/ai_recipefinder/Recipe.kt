package com.example.ai_recipefinder

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val duration: Int,
    val isFavorite: Boolean = false
)
