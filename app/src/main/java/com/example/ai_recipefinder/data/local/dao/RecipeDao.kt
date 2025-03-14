package com.example.ai_recipefinder.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ai_recipefinder.data.entity.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes WHERE title = :title LIMIT 1")
    suspend fun getRecipeByTitle(title: String): Recipe?

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("DELETE FROM recipes WHERE title = :title")
    suspend fun deleteRecipeByTitle(title: String)
}
