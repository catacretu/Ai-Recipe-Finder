package com.example.ai_recipefinder.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ai_recipefinder.R
import com.example.ai_recipefinder.componets.RecipeCard
import com.example.ai_recipefinder.componets.SearchBar
import com.example.ai_recipefinder.data.local.model.Recipe
import com.example.ai_recipefinder.viewmodel.RecipeViewModel

@Composable
fun HomeScreen(navController: NavController, recipeViewModel: RecipeViewModel) {
    val recipes by recipeViewModel.recipes.observeAsState(emptyList())
    val favoriteRecipes by recipeViewModel.favoriteRecipes.observeAsState(emptyList())
    val isLoading by recipeViewModel.isLoading.observeAsState(false)
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        SearchBar(
            searchQuery = searchQuery,
            onQueryChanged = { searchQuery = it },
            viewModel = recipeViewModel
        )
        Spacer(modifier = Modifier.height(35.dp))

        val displayedRecipes = if (searchQuery.isEmpty()) favoriteRecipes else recipes

        Text(
            if (!isLoading && searchQuery.isEmpty()) "Favorites" else "Suggested Recipes",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (displayedRecipes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(
                        if (searchQuery.isEmpty()) R.string.no_favorites_available_txt
                        else R.string.no_recipes_available_txt
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            RecipeList(
                recipeList = displayedRecipes,
                onRecipeSelected = { selectedRecipe ->
                recipeViewModel.selectRecipe(selectedRecipe)
                navController.navigate("recipe_details")
                },
                onFavoriteClick = { recipe ->
                    recipeViewModel.toggleFavorite(recipe)
                },
                onDislikeClicked = {
                    recipeViewModel.searchRecipes(searchQuery, true)
                },
                isLoading = isLoading,
                searchQuery = searchQuery
            )
        }
    }
}

@Composable
fun RecipeList(
    recipeList: List<Recipe>,
    onRecipeSelected: (Recipe) -> Unit,
    onFavoriteClick: (Recipe) -> Unit,
    onDislikeClicked: () -> Unit,
    isLoading: Boolean = false,
    searchQuery: String = ""
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(recipeList) { recipe ->
                RecipeCard(
                    recipe,
                    onFavoriteClick = { updatedRecipe ->
                        onFavoriteClick(updatedRecipe)
                    },
                    onRecipeClick = { selectedRecipe ->
                        onRecipeSelected(selectedRecipe)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!isLoading && searchQuery.isNotEmpty()) {
            Button(
                onClick = { onDislikeClicked() },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A52B3))
            ) {
                Text(text = "I don’t like these", color = Color.White)
            }
        }
    }
}

