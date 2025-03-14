package com.example.ai_recipefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_recipefinder.data.local.model.Recipe
import com.example.ai_recipefinder.data.remote.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.recipes
    val favoriteRecipes: LiveData<List<Recipe>> = repository.getAllRecipes()
    val isLoading: LiveData<Boolean> = repository.isLoading
    private val _selectedRecipe = MutableLiveData<Recipe?>()
    val selectedRecipe: LiveData<Recipe?> = _selectedRecipe

    init {
        getAllFavouriteRecipes()
    }

    fun searchRecipes(query: String, additionalSearch: Boolean = false) {
        viewModelScope.launch {
            repository.fetchRecipes(query, additionalSearch)
        }
    }
    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun getAllFavouriteRecipes(): LiveData<List<Recipe>> {
        return repository.getAllRecipes()
    }

    fun deleteRecipeById(recipeId: Int) {
        viewModelScope.launch {
            repository.deleteRecipeById(recipeId)
        }
    }

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            repository.toggleFavorite(recipe)
        }
    }
}

