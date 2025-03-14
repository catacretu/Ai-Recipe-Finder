package com.example.ai_recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ai_recipefinder.componets.NavigationGraph
import com.example.ai_recipefinder.ui.theme.AiRecipeFinderTheme
import com.example.ai_recipefinder.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recipeViewModel: RecipeViewModel = hiltViewModel()
            AiRecipeFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    NavigationGraph(recipeViewModel)
                }
            }
        }
    }
}
