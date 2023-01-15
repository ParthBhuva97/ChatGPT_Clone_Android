package com.idea.chatapp

import retrofit2.http.Query

data class CompletionRequest(
    @Query("prompt") val prompt: String,
    @Query("model") val model: String,
    @Query("temperature") val temperature: Float?,
    @Query("max_tokens") val max_tokens: Int?
)

