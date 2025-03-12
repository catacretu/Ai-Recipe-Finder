package com.example.ai_recipefinder

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

object RecipeListSaver : Saver<SnapshotStateList<Recipe>, List<String>> {

    override fun restore(value: List<String>): SnapshotStateList<Recipe> {
        return value.map {
            val parts = it.split(",")
            Recipe(
                id = parts[0].toInt(),
                title = parts[1],
                imageUrl = parts[2],
                duration = parts[3].toInt(),
                isFavorite = parts[4].toBoolean()
            )
        }.toMutableStateList()
    }

    override fun SaverScope.save(value: SnapshotStateList<Recipe>): List<String>? {
        return value.map { "${it.id},${it.title},${it.imageUrl},${it.duration},${it.isFavorite}" }
    }
}
