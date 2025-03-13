package com.example.ai_recipefinder.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.ai_recipefinder.R
import com.example.ai_recipefinder.componets.RecipeCard
import com.example.ai_recipefinder.componets.SearchBar
import com.example.ai_recipefinder.data.entity.Recipe
import com.example.ai_recipefinder.data.saver.RecipeListSaver
import com.example.ai_recipefinder.viewmodel.RecipeViewModel

@Composable
fun HomeScreen(viewModel: RecipeViewModel) {
    val recipes by viewModel.recipes.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        SearchBar(viewModel)
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            "Favorites",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (recipes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_recipes_available_txt),
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            RecipeList(recipes)
        }
    }
}

@Composable
fun RecipeList(recipeList: List<Recipe>) {
    var recipes by rememberSaveable(stateSaver = RecipeListSaver) {
        mutableStateOf(recipeList)
    }

    LazyColumn {
        items(recipes) { recipe ->
            RecipeCard(recipe) {
                val index = recipes.indexOf(recipe)
                if (index != -1) {
                    recipes = recipes.toMutableList().apply {
                        this[index] = recipe.copy(isFavourite = !recipe.isFavourite)
                    }
                }
            }
        }
    }
}

