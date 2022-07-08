package com.example.foodii.homePage.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.foodii.MainActivity
import com.example.foodii.R
import com.example.foodii.adapter.Adapter
import com.example.foodii.adapter.AdapterCallback
import com.example.foodii.mealDetails.ui.MealDetailActivity
import com.example.foodii.databinding.FragmentFirstBinding
import com.example.foodii.homePage.model.Category
import com.example.foodii.homePage.model.Meal
import com.example.foodii.homePage.model.PopularMealItems
import com.example.foodii.homePage.repo.HomeRepo
import com.example.foodii.homePage.viewModel.HomeViewModel
import com.example.foodii.homePage.viewModel.ViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
 //   private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var viewModel: HomeViewModel
    private lateinit var result : Meal
    private  var popularList: List<PopularMealItems> = emptyList()
    private lateinit var adapterPopularMeal : Adapter<PopularMealItems>
    private var categoriesList: List<Category> = listOf(Category("11","category","downloading", "xxx"))
    private lateinit var adapterCategory: Adapter<Category>
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object{
        val MEAL_NAME= "mealName"
        val MEAL_Image= "mealImage"
        val MEAL_ID= "mealID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory(HomeRepo()))[HomeViewModel::class.java]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            adapterCategory = Adapter<Category>(it.applicationContext)
                .adapterCallback(categoryAdapterCallback)
                .setLayout(R.layout.category_card)
                .setGridLayout(3)
                .addData(categoriesList)
                .build(binding.recyclerViewCategory)
        }
        loadData()
        binding.materialCardView.setOnClickListener {
            val i = Intent(activity, MealDetailActivity::class.java)
            i.putExtra(MEAL_NAME, result.strMeal)
            i.putExtra(MEAL_ID, result.idMeal)
            i.putExtra(MEAL_Image, result.strMealThumb)
            startActivity(i)
        }
    }

    private fun loadData() {
        viewModel.randomMealData.observe(viewLifecycleOwner){
            Log.d("test","$it")
            result =it;
            Glide.with(this)
                .load(it.strMealThumb)
                .into(binding.bannerImage)
        }

        viewModel.popularMealList.observe(viewLifecycleOwner){
            popularList= emptyList()
            for (items in it){
                popularList = popularList+ items
            }
            activity?.let {
                Toast.makeText(activity, "Hua", Toast.LENGTH_SHORT).show()
                adapterPopularMeal= Adapter<PopularMealItems>(it.applicationContext)
                    .adapterCallback(PopularMealAdapterCallback)
                    .setLayout(R.layout.popular_card)
                    .isHorizontalView()
                    .addData(popularList)
                    .build(binding.recyclerViewPopularMeal)
            }
        }

        viewModel.categoriesList.observe(viewLifecycleOwner){
            categoriesList = emptyList()
//            it.forEach { _item ->
//                adapterCategory.updateData(_item)
//                Log.d("yeye","$_item")
//            }

            for (item in it){
                categoriesList = categoriesList+ item
            }
            adapterCategory.addData(categoriesList)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val PopularMealAdapterCallback = object : AdapterCallback<PopularMealItems> {
        override fun initComponent(itemView: View, data: PopularMealItems, itemIndex: Int) {
            Glide.with(this@FirstFragment)
                .load(data.strMealThumb)
                .into(itemView.findViewById(R.id.popularImgCard))
        }

        override fun onItemClicked(itemView: View, data: PopularMealItems, itemIndex: Int) {
            Toast.makeText(activity, "$data", Toast.LENGTH_SHORT).show()
        }
    }

    private val categoryAdapterCallback = object : AdapterCallback<Category>{
        override fun initComponent(itemView: View, data: Category, itemIndex: Int) {
            val img =itemView.findViewById<ImageView>(R.id.categoryImg)
            Glide.with(this@FirstFragment)
                .load(data.strCategoryThumb)
                .into(img)

            itemView.findViewById<TextView>(R.id.categoryName)
                .text= data.strCategory
        }

        override fun onItemClicked(itemView: View, data: Category, itemIndex: Int) {

        }

    }
}