package com.example.ai_recipefinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ai_recipefinder.data.entity.Recipe
import com.example.ai_recipefinder.data.local.converters.Converters
import com.example.ai_recipefinder.data.local.dao.RecipeDao


@Database(entities = [Recipe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
