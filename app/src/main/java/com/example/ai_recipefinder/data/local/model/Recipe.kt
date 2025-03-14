package com.example.ai_recipefinder.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val time: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: String,
    var isFavourite: Boolean = false
)