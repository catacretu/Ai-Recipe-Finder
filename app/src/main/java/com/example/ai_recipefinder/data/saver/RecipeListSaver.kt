package com.example.ai_recipefinder.data.saver

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import com.example.ai_recipefinder.data.entity.Recipe

object RecipeListSaver : Saver<List<Recipe>, List<Map<String, Any>>> {
    override fun restore(value: List<Map<String, Any>>): List<Recipe> {
        return value.map {
            Recipe(
                title = it["title"] as String,
                time = it["time"] as String,
                imageUrl = it["imageUrl"] as String,
                ingredients = it["ingredients"] as List<String>,
                instructions = it["instructions"] as String,
                isFavourite = it["isFavourite"] as Boolean
            )
        }
    }

    override fun SaverScope.save(value: List<Recipe>): List<Map<String, Any>>? {
        return value.map {
            mapOf(
                "title" to it.title,
                "time" to it.time,
                "imageUrl" to it.imageUrl,
                "ingredients" to it.ingredients,
                "instructions" to it.instructions,
                "isFavourite" to it.isFavourite
            )
        }
    }
}


