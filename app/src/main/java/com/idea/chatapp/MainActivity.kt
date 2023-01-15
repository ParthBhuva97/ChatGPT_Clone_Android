package com.idea.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<FloatingActionButton>(R.id.btn)
        val input = findViewById<TextInputEditText>(R.id.question)
        val answer = findViewById<TextView>(R.id.answer)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val openAiApiService = retrofit.create(OpenAiApiService::class.java)

        btn.setOnClickListener {
            val completionRequest = CompletionRequest(input.text.toString(), "text-davinci-003",0F,1000)
            val call = openAiApiService.getCompletion(completionRequest)

            call.enqueue(object : Callback<CompletionResponse> {
                override fun onResponse(call: Call<CompletionResponse>, response: Response<CompletionResponse>) {
                    if (response.isSuccessful) {

                        val completionResponse = response.body()
                        val choices = completionResponse?.choices
                        val text = choices?.get(0)?.text
                        answer.text = text.toString()
                        input.text = null
                    } else {
                        Log.i("Error msg", "onResponse: ${response.errorBody()?.string()}")
                        Toast.makeText(this@MainActivity,response.errorBody()?.string(),Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CompletionResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Request Timed Out. Please try again.",Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}






