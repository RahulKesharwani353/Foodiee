package com.example.foodii.homePage.api

import com.example.foodii.homePage.model.CategoriesList
import com.example.foodii.homePage.model.PopularMealList
import com.example.foodii.homePage.model.RandomMeal
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MealApiService {

    @GET("random.php")
    suspend fun getRandomMeal() : Response<RandomMeal>

    @GET("filter.php?a=Italian")
    suspend fun getPopularMeal(): Response<PopularMealList>

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoriesList>
}