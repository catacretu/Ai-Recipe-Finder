package com.example.ai_recipefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_recipefinder.data.entity.Recipe
import com.example.ai_recipefinder.data.remote.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.recipes
    val isLoading: LiveData<Boolean> = repository.isLoading

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            repository.fetchRecipes(query)
        }
    }
}

