package com.example.takeaway.repository

import com.example.takeaway.ui.data.db.entity.Restaurant
import com.example.takeaway.ui.data.db.entity.SortingValues
import com.example.takeaway.ui.data.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAppRepository : BaseRepository {
    private val restaurants: MutableMap<Int, Restaurant> = getFakeRestaurants()

    /**
     * Simulates LiveData for getRestaurants() function.
     */
    private var observableRestaurants: Flow<List<Restaurant>> = flowOf(restaurants.values.toList())

    override suspend fun getRestaurants(): Flow<List<Restaurant>> {
        return observableRestaurants
    }


    private fun getFakeRestaurants(): MutableMap<Int, Restaurant> {
        val sampleSortingValues = SortingValues(1, 1.1, 1.1, 10, 1.1, 1, 1, 1)
        val sortingValuesMinDistance = SortingValues(10, 1.1, 1.1, 1, 1.1, 1, 1, 1)
        val restaurant1 = Restaurant("Restaurant1", "open", sortingValuesMinDistance, id = 1)
        val restaurant2 = Restaurant("Restaurant2", "Closed", sampleSortingValues, id = 2)
        val restaurant3 = Restaurant("Restaurant3", "Order ahead", sampleSortingValues, id = 3)

        return mutableMapOf(1 to restaurant1, 2 to restaurant2, 3 to restaurant3)
    }

    companion object {
        const val ITEM_COUNT = 3
    }
}