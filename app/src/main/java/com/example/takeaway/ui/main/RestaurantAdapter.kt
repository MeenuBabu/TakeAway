package com.example.takeaway.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.takeaway.R
import com.example.takeaway.databinding.ItemListRestaurantBinding
import com.example.takeaway.ui.data.db.entity.Restaurant

/**
 * Adapter class to populate the recyclerview with restaurant list
 * */
class RestaurantAdapter(

    private var context: Context,
    private var itemList: List<Restaurant>

) : RecyclerView.Adapter<RestaurantAdapter.ItemViewHolder>() {
    private var sortingOption: SortOption = SortOption.BestMatch

    fun updateFilteredList(filteredRestaurantList: List<Restaurant>) {
        this.itemList = filteredRestaurantList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantAdapter.ItemViewHolder {
        val binding =
            ItemListRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantAdapter.ItemViewHolder, position: Int) {

        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(private val binding: ItemListRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.apply {
                tvRestaurant.text = restaurant.name
                tvOpeningStatus.text = restaurant.status
                tvSortValue.text = context.getString(R.string.sort_value, getSortValue(restaurant))

            }
        }
    }

    fun setSortOption(sortType: SortOption) {
        sortingOption = sortType
        notifyDataSetChanged()
    }

    private fun getSortValue(restaurant: Restaurant): String {
        return when (sortingOption) {
            SortOption.BestMatch -> restaurant.sortingValues.bestMatch.toString()
            SortOption.Newest -> restaurant.sortingValues.newest.toString()
            SortOption.RatingAverage -> restaurant.sortingValues.ratingAverage.toString()
            SortOption.Distance -> restaurant.sortingValues.distance.toString()
            SortOption.Popularity -> restaurant.sortingValues.popularity.toString()
            SortOption.AverageProductPrice -> restaurant.sortingValues.averageProductPrice.toString()
            SortOption.DeliveryCosts -> restaurant.sortingValues.deliveryCosts.toString()
            SortOption.MinCost -> restaurant.sortingValues.minCost.toString()
        }
    }
}