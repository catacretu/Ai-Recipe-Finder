package com.example.ai_recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ai_recipefinder.componets.NavigationGraph
import com.example.ai_recipefinder.data.remote.RecipeRepository
import com.example.ai_recipefinder.data.remote.RetrofitClient
import com.example.ai_recipefinder.ui.theme.AiRecipeFinderTheme
import com.example.ai_recipefinder.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiRecipeFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    val recipeRepository = RecipeRepository(RetrofitClient.api)
                    val recipeViewModel = remember { RecipeViewModel(recipeRepository) }
                    NavigationGraph(recipeViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AiRecipeFinderTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        )
        {
            val recipeRepository = RecipeRepository(RetrofitClient.api)
            val recipeViewModel = remember { RecipeViewModel(recipeRepository) }
            NavigationGraph(recipeViewModel)
        }
    }
}