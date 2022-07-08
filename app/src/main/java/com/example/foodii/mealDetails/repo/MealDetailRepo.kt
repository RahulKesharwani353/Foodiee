package com.example.foodii.mealDetails.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodii.mealDetails.model.Meal

import com.example.foodii.network.RetrofitHelper

class MealDetailRepo {
    private val mealDetail  = MutableLiveData<Meal>()
    val mealDetailLiveData : LiveData<Meal> get() = mealDetail
    suspend fun getMealDetails(id: String){
        val result = RetrofitHelper.mealDetailsApiService.getMealDetails(id)
        if (result.body()!=null){
            mealDetail.postValue(result.body()!!.meals[0])
        }
    }
}