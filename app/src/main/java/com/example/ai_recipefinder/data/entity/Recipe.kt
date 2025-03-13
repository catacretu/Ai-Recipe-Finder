package com.example.ai_recipefinder.data.entity

data class Recipe(
    val title: String,
    val time: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: String,
    val isFavourite: Boolean = false
)