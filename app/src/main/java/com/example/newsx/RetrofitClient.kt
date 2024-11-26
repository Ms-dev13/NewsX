package com.example.newsx

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
        // Define your base URL
        private const val BASE_URL = "https://newsapi.org/v2/"

        val instance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL) // Set the base URL
                .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
                .build()
        }
    }
        object ApiClient{
            val apiservice:ApInterface by lazy {
                RetrofitClient.instance.create(ApInterface::class.java);
            }
        }