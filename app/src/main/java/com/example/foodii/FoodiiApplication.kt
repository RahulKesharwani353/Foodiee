package com.example.foodii

import android.app.Application
import com.example.foodii.homePage.api.MealApiService
import com.example.foodii.homePage.repo.HomeRepo
import com.example.foodii.network.RetrofitHelper

class FoodiiApplication:Application() {

    lateinit var homeRepo: HomeRepo

    override fun onCreate() {
        super.onCreate()
        initilizer()
    }

    private fun initilizer() {
        homeRepo =HomeRepo()
    }

}