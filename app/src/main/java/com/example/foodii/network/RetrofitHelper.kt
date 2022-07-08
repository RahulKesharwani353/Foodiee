package com.example.foodii.network

import com.example.foodii.homePage.api.MealApiService
import com.example.foodii.mealDetails.api.MealDetailApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private  val base_url = "https://www.themealdb.com/api/json/v1/1/"

    val api: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }
    val mealDetailsApiService: MealDetailApiService by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealDetailApiService::class.java)
    }

}