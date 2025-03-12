package com.example.ai_recipefinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val recipes = listOf(
        Recipe(id = 1, title = "Recipe 1", "", duration = 20, isFavorite = false),
        Recipe(id = 2, title = "Recipe 2", "", duration = 20, isFavorite = true),
        Recipe(id = 3, title = "Recipe 3", "", duration = 20, isFavorite = false),
        Recipe(id = 4, title = "Recipe 4", "", duration = 20, isFavorite = true)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        SearchBar(searchQuery) { searchQuery = it }
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            "Favorites",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        RecipeList(recipes)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        trailingIcon = {
            IconButton(onClick = { /* Acțiune la apăsare */ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        placeholder = { Text("What do you feel like eating?") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions.Default,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray
        )
    )
}

@Composable
fun RecipeList(recipeList: List<Recipe>) {
    val recipes = rememberSaveable(saver = RecipeListSaver) {
        mutableStateListOf(*recipeList.toTypedArray())
    }


    LazyColumn {
        items(recipes) { recipe ->
            RecipeCard(recipe) {
                val index = recipes.indexOfFirst { it.id == recipe.id }
                if (index != -1) {
                    recipes[index] = recipe.copy(isFavorite = !recipe.isFavorite)
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onFavoriteClick: (Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                Image(
                    painterResource(android.R.drawable.ic_menu_gallery),
                    contentDescription = "Recipe Image"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(recipe.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("20 min.", fontSize = 14.sp, modifier = Modifier.padding(top = 7.dp))
            }
            IconButton(onClick = { onFavoriteClick(recipe.copy(isFavorite = !recipe.isFavorite)) }) {
                Icon(
                    imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favorite",
                    tint = if (recipe.isFavorite) Color(0xFF6D28D9) else Color.LightGray
                )
            }
        }
    }
}