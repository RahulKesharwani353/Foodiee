package com.example.foodii.mealDetails.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.foodii.databinding.ActivityMealDetailBinding
import com.example.foodii.homePage.ui.FirstFragment
import com.example.foodii.mealDetails.viewModel.MealDetailViewModel
import kotlinx.coroutines.launch

class MealDetailActivity : AppCompatActivity() {

private lateinit var binding: ActivityMealDetailBinding
private lateinit var mealName: String
private lateinit var mealId: String
private lateinit var mealImg: String
private val viewModel: MealDetailViewModel by viewModels()
//private lateinit var viewModel: MealDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMealDetailBinding.inflate(layoutInflater)
        mealName= intent.getStringExtra(FirstFragment.MEAL_NAME).toString()
        mealId = intent.getStringExtra(FirstFragment.MEAL_ID).toString()
        mealImg = intent.getStringExtra(FirstFragment.MEAL_Image).toString()
//        viewModel = ViewModelProviders.of(this)[MealDetailViewModel::class.java]
     setContentView(binding.root)

        viewModel.viewModelScope.launch { viewModel.getMealDetails(mealId) }

    }

    override fun onStart() {
        super.onStart()
        binding.imgYoutube.setOnClickListener {
            Snackbar.make(findViewById(android.R.id.content), "YT DEKHE", Snackbar.LENGTH_SHORT).show()
        }

        setDatas()
    }

    private fun setDatas() {
        Glide.with(this).load(mealImg)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName

        viewModel.mealDetailsData.observe(this){
            binding.tvInstructions.text = it.strInstructions
        }

    }

}
