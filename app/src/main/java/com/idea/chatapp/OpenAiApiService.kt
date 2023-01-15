package com.idea.chatapp

import retrofit2.Call
import retrofit2.http.*

interface OpenAiApiService {
    @POST("completions")
    @Headers("Content-Type: application/json", "Authorization: Bearer <YOUR_API_KEY>")
    fun getCompletion(@Body request: CompletionRequest): Call<CompletionResponse>
}
