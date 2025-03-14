package com.example.ai_recipefinder.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ai_recipefinder.data.entity.ChatRequest
import com.example.ai_recipefinder.data.entity.Message
import com.example.ai_recipefinder.data.local.dao.RecipeDao
import com.example.ai_recipefinder.data.local.model.Recipe
import org.json.JSONArray
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeDao: RecipeDao
) {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun fetchRecipes(query: String) {
        _isLoading.postValue(true)

        val request = ChatRequest(
            messages = listOf(
                Message(
                    "system",
                    "You are a helpful assistant that returns recipes in JSON format and each recipe must include a real image URL that exists on the internet.."
                ),
                Message(
                    "user",
                    "Generate 4 recipes for $query. Format response as a JSON array with objects containing title, time, imageUrl, ingredients (list), and instructions."
                )
            )
        )

        try {
            val response = recipeService.getRecipes(request)
            val jsonContent = response.choices.firstOrNull()?.message?.content ?: ""
            if (jsonContent.isBlank()) {
                _recipes.postValue(emptyList())
                return
            }
            val cleanedJson = jsonContent.trim().removeSurrounding("```json", "```").trim()
            val recipesArray = JSONArray(cleanedJson)

            val recipeList = (0 until recipesArray.length()).map {
                val obj = recipesArray.getJSONObject(it)
                Recipe(
                    title = obj.getString("title"),
                    time = obj.getString("time"),
                    imageUrl = obj.getString("imageUrl"),
                    ingredients = obj.getJSONArray("ingredients").let { arr ->
                        List(arr.length()) { index -> arr.getString(index) }
                    },
                    instructions = obj.getString("instructions")
                )
            }
            _recipes.postValue(recipeList)
        } catch (e: Exception) {
            _recipes.postValue(emptyList())
        } finally {
            _isLoading.postValue(false)
        }
    }
}
