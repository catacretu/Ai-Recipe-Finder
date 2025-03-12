package com.example.ai_recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ai_recipefinder.ui.theme.AiRecipeFinderTheme

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
                    HomeScreen()
                }
            }
        }
    }
}

//@Composable
//fun HomeScreen(modifier: Modifier = Modifier) {
//    Text(text = "Hello",
//        modifier = modifier
//    )
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AiRecipeFinderTheme {
        HomeScreen()
    }
}