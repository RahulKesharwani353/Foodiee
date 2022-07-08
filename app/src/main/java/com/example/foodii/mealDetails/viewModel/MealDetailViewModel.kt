package com.example.foodii.mealDetails.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.foodii.homePage.model.Meal
import com.example.foodii.mealDetails.repo.MealDetailRepo

class MealDetailViewModel:ViewModel() {

    private val repo = MealDetailRepo()
    suspend fun getMealDetails(id: String){
        Log.d( "bhai", "MealVM")
        repo.getMealDetails(id)
    }
    val mealDetailsData get() = repo.mealDetailLiveData
}