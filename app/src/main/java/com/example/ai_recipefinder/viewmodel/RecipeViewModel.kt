package com.example.ai_recipefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_recipefinder.data.entity.Recipe
import com.example.ai_recipefinder.data.remote.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.recipes
    val favoriteRecipes: LiveData<List<Recipe>> = repository.getFavoriteRecipes()
    val isLoading: LiveData<Boolean> = repository.isLoading

    private val _selectedRecipe = MutableLiveData<Recipe?>()
    val selectedRecipe: LiveData<Recipe?> = _selectedRecipe

    init {
        loadFavoriteRecipes()
    }

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            repository.toggleFavorite(recipe)
        }
    }

    private fun loadFavoriteRecipes() {
        viewModelScope.launch {
            repository.getFavoriteRecipes()
        }
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            repository.fetchRecipes(query)
        }
    }

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }
}


