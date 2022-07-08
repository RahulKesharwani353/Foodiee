package com.example.foodii.homePage.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodii.homePage.api.MealApiService
import com.example.foodii.homePage.model.CategoriesList
import com.example.foodii.homePage.model.Category
import com.example.foodii.homePage.model.Meal
import com.example.foodii.homePage.model.PopularMealItems
import com.example.foodii.homePage.repo.HomeRepo
import kotlinx.coroutines.launch

class HomeViewModel(private var repo: HomeRepo): ViewModel(){

    init{
        viewModelScope.launch {
            getPopularItems()
            getCategory()
            getRandomImage()
        }
    }
    private suspend fun getRandomImage(){
        repo.getRandomMealImage()
    }

    private suspend fun getPopularItems(){
        repo.getPopularMeal()
    }
    private suspend fun getCategory(){
        repo.getCategories()
    }
    val randomMealData: LiveData<Meal> get() = repo.randomMeal
    val popularMealList: LiveData<List<PopularMealItems>> get() = repo.popularMealLiveData
    val categoriesList : LiveData<List<Category>> get()= repo.categoriesListLiveData
}