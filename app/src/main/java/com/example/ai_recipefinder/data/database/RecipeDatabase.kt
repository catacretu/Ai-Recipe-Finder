package com.example.ai_recipefinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ai_recipefinder.data.local.dao.RecipeDao
import com.example.ai_recipefinder.data.local.model.Converters
import com.example.ai_recipefinder.data.local.model.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}