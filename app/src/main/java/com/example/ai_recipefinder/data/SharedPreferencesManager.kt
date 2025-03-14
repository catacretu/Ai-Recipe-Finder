package com.example.ai_recipefinder.data

import android.content.Context
import com.example.ai_recipefinder.data.entity.Recipe
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SharedPreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("recipe_prefs", Context.MODE_PRIVATE)

    fun saveFavorites(favorites: List<Recipe>) {
        val json = Json.encodeToString(favorites)
        prefs.edit().putString("favorites", json).apply()
    }

    fun getFavorites(): List<Recipe> {
        val json = prefs.getString("favorites", null) ?: return emptyList()
        return try {
            Json.decodeFromString(json)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
