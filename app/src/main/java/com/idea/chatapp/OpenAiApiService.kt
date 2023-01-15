package com.idea.chatapp

import retrofit2.Call
import retrofit2.http.*

interface OpenAiApiService {
    @POST("completions")
    @Headers("Content-Type: application/json", "Authorization: Bearer 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")
    fun getCompletion(@Body request: CompletionRequest): Call<CompletionResponse>
}
