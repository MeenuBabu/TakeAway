package com.example.takeaway.ui.main


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.takeaway.R
import com.example.takeaway.databinding.ActivityRestaurantBinding
import com.example.takeaway.databinding.ActivityRestaurantSearchBarBinding
import com.example.takeaway.databinding.ActivityRestaurantToolbarBinding
import com.example.takeaway.ui.data.db.entity.Restaurant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_restaurant_search_bar.*
import java.util.*

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding
    private lateinit var toolbarBinding: ActivityRestaurantToolbarBinding
    private lateinit var searchBinding: ActivityRestaurantSearchBarBinding

    private lateinit var restaurantAdapter: RestaurantAdapter
    private var restaurantList: List<Restaurant> = arrayListOf()
    private val viewModel: AppViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBinding = binding.activityMainToolbar
        searchBinding = binding.activityMainSearchBar
        initToolbar()
        initSpinner()
        loadData()
    }

    /**
     * Search Actions
     * */
    private fun initToolbar() {
        toolbarBinding.mainToolbarSearchIcon.setOnClickListener { performSearch() }
        searchBinding.ivSearchCancel.setOnClickListener {
            resetSearch()
            it.hideKeyboard()
        }
    }

    /**
     * Display data in the recyclerview
     * */
    private fun loadData() {
        viewModel.getRestaurants().observe(this) {
            restaurantList = it
            binding.rvRestaurants.layoutManager = LinearLayoutManager(this)
            restaurantAdapter =
                RestaurantAdapter(
                    this,
                    restaurantList
                )
            restaurantAdapter.setSortOption(viewModel.sortOption)
            binding.rvRestaurants.adapter = restaurantAdapter

        }
    }

    /**
     * Search restaurant functionality
     * */
    private fun performSearch() {

        toolbarBinding.mainToolbar.visibility = View.GONE
        searchBinding.mainSearchBarParent.visibility = View.VISIBLE
        atv_search.apply {
            addTextChangedListener {
                val searchText = text.toString().lowercase(Locale.getDefault())

                val filteredList = getFilteredRestaurants(searchText)
                if (filteredList.isEmpty()) {
                    val message: String = context.getString(R.string.restaurant_not_found)
                    handleListVisibility(false, message)
                } else {
                    handleListVisibility(true, null)
                    restaurantAdapter.updateFilteredList(filteredList)

                }
            }
        }
    }

    /**
     * Filter restaurants based on entered text
     * */
    private fun getFilteredRestaurants(searchText: String): List<Restaurant> {
        return if (searchText.isEmpty()) restaurantList
        else restaurantList.filter {
            it.name.lowercase(Locale.getDefault()).contains(searchText)
        } as ArrayList<Restaurant>
    }

    /**
     * Function to handle visibility of recyclerview
     * */
    private fun handleListVisibility(show: Boolean, message: String?) {
        if (show) {
            binding.rvRestaurants.visibility = View.VISIBLE
            binding.tvNotFound.visibility = View.GONE
        } else {
            binding.rvRestaurants.visibility = View.GONE
            binding.tvNotFound.visibility = View.VISIBLE
            binding.tvNotFound.text = message
        }
    }

    private fun resetSearch() {
        toolbarBinding.mainToolbar.visibility = View.VISIBLE
        searchBinding.atvSearch.setText("")
        searchBinding.mainSearchBarParent.visibility = View.GONE
    }

    /**
     * Show spinner Function
     * */
    private fun initSpinner() {
        binding.spinnerFilter.apply {
            setSelection(viewModel.sortOption.ordinal)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(aV: AdapterView<*>?, v: View?, position: Int, i: Long) {
                    viewModel.sortOption = SortOption.values()[position]
                    loadData()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


}