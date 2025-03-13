package com.example.ai_recipefinder.data.remote

import com.example.ai_recipefinder.OPEN_API_KEY
import com.example.ai_recipefinder.data.entity.ChatRequest
import com.example.ai_recipefinder.data.entity.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RecipeService {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $OPEN_API_KEY"
    )
    @POST("v1/chat/completions")
    suspend fun getRecipes(@Body request: ChatRequest): ChatResponse
}