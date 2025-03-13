package com.example.ai_recipefinder.componets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ai_recipefinder.viewmodel.RecipeViewModel
import com.example.ai_recipefinder.views.HomeScreen
import com.example.ai_recipefinder.views.RecipeDetailsScreen

@Composable
fun NavigationGraph(recipeViewModel: RecipeViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    )
    {
        composable("home_screen") {
            HomeScreen(navController, recipeViewModel)
        }

        composable("recipe_details") {
            val selectedRecipe by recipeViewModel.selectedRecipe.observeAsState()

            if (selectedRecipe != null) {
                RecipeDetailsScreen(selectedRecipe!!) {
                    navController.popBackStack()
                }
            }
        }
    }
}