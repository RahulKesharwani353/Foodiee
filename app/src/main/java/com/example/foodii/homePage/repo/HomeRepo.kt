package com.example.foodii.homePage.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.foodii.homePage.api.MealApiService
import com.example.foodii.homePage.model.*
import com.example.foodii.network.RetrofitHelper


class HomeRepo (

) {
    private val randomMealData = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> get() =randomMealData

    private val popularMealItems = MutableLiveData<List<PopularMealItems>>()
    val popularMealLiveData: LiveData<List<PopularMealItems>> get() =popularMealItems

    private val category = MutableLiveData<List<Category>>()
    val categoriesListLiveData: LiveData<List<Category>> get() =category

    suspend fun getRandomMealImage(){
        val result= RetrofitHelper.api.getRandomMeal()
        if (result.body() != null){
            Log.d("test","result: $result.body()!!.meals[0]")
            randomMealData.postValue(result.body()!!.meals[0])
        }
    }

    suspend fun getPopularMeal(){
        val result= RetrofitHelper.api.getPopularMeal()
        if (result.body() != null){
            Log.d("test","result: $result.body()!!.meals[0]")
            popularMealItems.postValue(result.body()!!.meals)
        }
    }

    suspend fun getCategories(){
        val result= RetrofitHelper.api.getCategories()
        if (result.body() != null){
            category.postValue(result.body()!!.categories)
        }
    }
}