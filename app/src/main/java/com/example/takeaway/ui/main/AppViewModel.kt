package com.example.takeaway.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takeaway.ui.data.db.entity.Restaurant
import com.example.takeaway.ui.data.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel  class for getting data from repository and supplying it to views
 * */
@HiltViewModel
class AppViewModel @Inject constructor(private val repository: BaseRepository) : ViewModel() {

    var sortOption = SortOption.BestMatch

    private val allItems = MediatorLiveData<List<Restaurant>>()

    /**
     * Fetches all data from repository and returns LiveData of restaurant list.
     */
    internal fun getRestaurants(): LiveData<List<Restaurant>> {
        viewModelScope.launch {
            repository.getRestaurants().collect { items ->
                allItems.value = sortItems(items)
            }
        }
        return allItems
    }

    /**
     * Function to sort items based on opening status and different sorting options
     * */
    private fun sortItems(items: List<Restaurant>) =
        items.sortedWith(
            compareBy<Restaurant> { OpenStatus.valueOf(it.status.uppercase().replace(" ", "_")) }
                .thenComparing(::compareBySortingOption)) // Sort based on Sorting Option
            .reversed()


    private fun compareBySortingOption(
        o1: Restaurant,
        o2: Restaurant
    ): Int {
        return when (sortOption) {
            SortOption.BestMatch -> o1.sortingValues.bestMatch.compareTo(o2.sortingValues.bestMatch)
            SortOption.Newest -> o1.sortingValues.newest.compareTo(o2.sortingValues.newest)
            SortOption.RatingAverage -> o1.sortingValues.ratingAverage.compareTo(o2.sortingValues.ratingAverage)
            SortOption.Distance -> -o1.sortingValues.distance.compareTo(o2.sortingValues.distance)
            SortOption.Popularity -> o1.sortingValues.popularity.compareTo(o2.sortingValues.popularity)
            SortOption.AverageProductPrice -> o1.sortingValues.averageProductPrice.compareTo(o2.sortingValues.averageProductPrice)
            SortOption.DeliveryCosts -> -o1.sortingValues.deliveryCosts.compareTo(o2.sortingValues.deliveryCosts)
            SortOption.MinCost -> -o1.sortingValues.minCost.compareTo(o2.sortingValues.minCost)
        }
    }

}
