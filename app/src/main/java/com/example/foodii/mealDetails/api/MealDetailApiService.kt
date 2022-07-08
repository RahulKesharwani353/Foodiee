package com.example.foodii.mealDetails.api

import com.example.foodii.mealDetails.model.MealDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDetailApiService {
    @GET("lookup.php?")
    suspend fun getMealDetails(@Query("i") id: String) : Response<MealDetails>
}