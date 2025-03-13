package com.example.ai_recipefinder.data.entity


data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
    val temperature: Double = 0.7
)
